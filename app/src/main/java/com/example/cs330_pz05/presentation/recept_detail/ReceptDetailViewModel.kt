package com.example.cs330_pz05.presentation.recept_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cs330_pz05.common.Constants
import com.example.cs330_pz05.common.Resource
import com.example.cs330_pz05.domain.use_case.get_recept.GetReceptUseCase
import com.example.cs330_pz05.domain.use_case.get_recepti.GetReceptiUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ReceptDetailViewModel @Inject constructor(
    private val getReceptUseCase: GetReceptUseCase,
    savedStateHandle: SavedStateHandle //kada kliknemo na neki recept prosledjujemo id bas tog recepta

): ViewModel() {

    private val _state = mutableStateOf(ReceptDetailState())
    val state: State<ReceptDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.RECEPT_ID)?.let { receptId ->
            getRecept(receptId)
        }
    }

    private fun getRecept(receptId:String){
        getReceptUseCase(receptId).onEach { result ->
            when(result){
                is Resource.Success ->{
                    _state.value = ReceptDetailState(recept = result.data)

                }
                is Resource.Error ->{
                    _state.value = ReceptDetailState(
                        error = result.message ?: "Doslo je do greske"
                    )

                }
                is Resource.Loading ->{
                    _state.value = ReceptDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



}