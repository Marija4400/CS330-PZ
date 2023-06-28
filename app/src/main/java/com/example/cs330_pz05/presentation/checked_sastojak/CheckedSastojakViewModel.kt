package com.example.cs330_pz05.presentation.checked_sastojak

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pz05.common.Resource
import com.example.cs330_pz05.domain.use_case.get_by_sastojak.GetReceptBySastojakUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CheckedSastojakViewModel @Inject constructor(
    private val getCheckedSastojakUseCase: GetReceptBySastojakUseCase
) : ViewModel(){

    private val _state = mutableStateOf(CheckedSastojakState())
    val state: State<CheckedSastojakState> = _state

    init{
       // getChecked()
    }

    private fun getChecked(sastojak: String){
        getCheckedSastojakUseCase(sastojak).onEach { result ->
            when(result){
                is Resource.Success ->{
                    _state.value = CheckedSastojakState(oznaceni = result.data?: emptyList())

                }
                is Resource.Error ->{
                    _state.value = CheckedSastojakState(
                        error = result.message ?: "Doslo je do greske"
                    )
                }
                is Resource.Loading ->{
                    _state.value = CheckedSastojakState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}