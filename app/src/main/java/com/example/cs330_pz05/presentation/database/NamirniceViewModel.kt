package com.example.cs330_pz05.presentation.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pz05.data.database.NamirniceEvent
import com.example.cs330_pz05.data.database.SortType
import com.example.cs330_pz05.data.database.dao.NamirniceDao
import com.example.cs330_pz05.domain.model.Namirnice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class NamirniceViewModel @Inject constructor(
    private val dao: NamirniceDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.TIP_PRODAVNICE)
    private val _namirnice = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.TIP_PRODAVNICE -> dao.getNamirniceOrderedByTipProdavnice()
                SortType.NAZIV -> dao.getNamirniceOrderedByNaziv()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(NamirniceState())
    val state = combine(_state, _sortType, _namirnice) { state, sortType, namirnice ->
        state.copy(
            namirnice = namirnice,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NamirniceState())

    fun onEvent(event: NamirniceEvent) {
        when(event) {
            is NamirniceEvent.DeleteNamirnice -> {
                viewModelScope.launch {
                    dao.deleteNamirnice(event.namirnice)
                }
            }
            NamirniceEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingNamirnice = false
                ) }
            }
            NamirniceEvent.SaveNamirnice-> {
                val tipProdavnice = state.value.tipProdavnice
                val naziv = state.value.naziv
                val kolicina = state.value.kolicina

                if(tipProdavnice.isBlank() || naziv.isBlank() || kolicina.isBlank()) {
                    return
                }

                val namirnice = Namirnice(
                    tipProdavnice = tipProdavnice,
                    naziv = naziv,
                    kolicina = kolicina
                )
                viewModelScope.launch {
                    dao.upsertNamirnice(namirnice)
                }
                _state.update { it.copy(
                    isAddingNamirnice = false,
                    tipProdavnice = "",
                    naziv = "",
                    kolicina = ""
                ) }
            }
            is NamirniceEvent.SetTipProdavnice -> {
                _state.update { it.copy(
                    tipProdavnice = event.tipProdavnice
                ) }
            }
            is NamirniceEvent.SetNaziv -> {
                _state.update { it.copy(
                    naziv = event.naziv
                ) }
            }
            is NamirniceEvent.SetKolicina -> {
                _state.update { it.copy(
                    kolicina = event.kolicina
                ) }
            }
            NamirniceEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingNamirnice = true
                ) }
            }
            is NamirniceEvent.SortNamirnice -> {
                _sortType.value = event.sortType
            }
        }
    }
}