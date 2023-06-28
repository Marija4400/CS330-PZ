package com.example.cs330_pz05.presentation.pretraga_recepata

import com.example.cs330_pz05.domain.model.Recept

data class PretragaRecepataState(
    val isLoading:Boolean = false,
    val recepti: List<Recept> = emptyList(),
    val error: String = "",
    val sastojci :String="",
    val dialog: Boolean = true
) {
}