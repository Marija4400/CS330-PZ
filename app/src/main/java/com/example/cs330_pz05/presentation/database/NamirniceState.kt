package com.example.cs330_pz05.presentation.database

import com.example.cs330_pz05.data.database.SortType
import com.example.cs330_pz05.domain.model.Namirnice

data class NamirniceState(
    val namirnice: List<Namirnice> = emptyList(),
    val tipProdavnice : String = "",
    val naziv: String = "",
    val kolicina: String = "",
    val isAddingNamirnice: Boolean = false,
    val sortType: SortType = SortType.NAZIV
) {
}