package com.example.cs330_pz05.presentation.checked_sastojak

import com.example.cs330_pz05.domain.model.Recept

data class CheckedSastojakState(
    val isLoading: Boolean = false,
    val oznaceni : List<Recept> = emptyList(),
    val error : String = ""
) {
}