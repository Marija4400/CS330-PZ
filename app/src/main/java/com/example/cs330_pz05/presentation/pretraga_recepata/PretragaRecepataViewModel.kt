package com.example.cs330_pz05.presentation.pretraga_recepata

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pz05.common.Resource
import com.example.cs330_pz05.domain.use_case.get_by_sastojak.GetReceptBySastojakUseCase
import com.example.cs330_pz05.domain.use_case.get_recepti.GetReceptiUseCase
import com.example.cs330_pz05.presentation.recept_list.ReceptListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PretragaRecepataViewModel @Inject constructor(
    private val getReceptBySastojakUseCase: GetReceptBySastojakUseCase
): ViewModel() {

    private val _state = mutableStateOf(PretragaRecepataState())
    val state: State<PretragaRecepataState> = _state


    fun setPtretraga(sastojci: String){
        _state.value = _state.value.copy(sastojci = sastojci)
    }
    fun setDialog(){
        _state.value = _state.value.copy(
            dialog = false
        )
    }

    fun getReceptiBySastojak(sastjci: String){
        getReceptBySastojakUseCase(sastjci).onEach { result ->
            when(result){
                is Resource.Success ->{
                    //azurira vrednosti _state
                    _state.value = _state.value.copy(recepti = result.data ?: emptyList(),
                    isLoading = false,
                    error = "")
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(
                        error = result.message ?: "Doslo je do greske"
                    )

                }
                is Resource.Loading ->{
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}