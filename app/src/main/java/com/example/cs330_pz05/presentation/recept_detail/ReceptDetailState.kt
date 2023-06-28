package com.example.cs330_pz05.presentation.recept_detail

import com.example.cs330_pz05.domain.model.Recept
import com.example.cs330_pz05.domain.model.ReceptDetail

data class ReceptDetailState(
    val isLoading:Boolean = false,
    val recept: ReceptDetail? = null,
    val error: String = ""
)
