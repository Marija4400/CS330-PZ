package com.example.cs330_pz05.presentation.recept_list

import com.example.cs330_pz05.domain.model.Recept

data class ReceptListState(
    val isLoading:Boolean = false,
    val recepti: List<Recept> = emptyList(),
    val error: String = "",
    val searched: List<Recept> = emptyList(),
    val isSearching : Boolean = false,
    val sastojci: String = ""
)
