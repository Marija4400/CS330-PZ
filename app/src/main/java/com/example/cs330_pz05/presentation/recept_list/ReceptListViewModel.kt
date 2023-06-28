package com.example.cs330_pz05.presentation.recept_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pz05.common.Resource
import com.example.cs330_pz05.domain.use_case.get_by_sastojak.GetReceptBySastojakUseCase
import com.example.cs330_pz05.domain.use_case.get_recepti.GetReceptiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ReceptListViewModel @Inject constructor(
    private val getReceptiUseCase: GetReceptiUseCase,
    private val getReceptBySastojakUseCase: GetReceptBySastojakUseCase
): ViewModel() {

    private val _state = mutableStateOf(ReceptListState())
    val state: State<ReceptListState> = _state

    init {
        getRecepti()
    }

    fun getReceptiBySastojak(sastojci: String){
        getReceptBySastojakUseCase(sastojci).onEach { result ->
            when(result){
                is Resource.Success ->{
                    _state.value = ReceptListState(searched = result.data ?: emptyList(),
                    isSearching = true,
                    isLoading = false)

                }
                is Resource.Error ->{
                    _state.value = ReceptListState(
                        error = result.message ?: "Doslo je do greske"
                    )

                }
                is Resource.Loading ->{
                    _state.value = ReceptListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRecepti(){
        getReceptiUseCase().onEach { result ->
            when(result){
                is Resource.Success ->{
                    _state.value = ReceptListState(recepti = result.data ?: emptyList())

                }
                is Resource.Error ->{
                    _state.value = ReceptListState(
                        error = result.message ?: "Doslo je do greske"
                    )

                }
                is Resource.Loading ->{
                    _state.value = ReceptListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}