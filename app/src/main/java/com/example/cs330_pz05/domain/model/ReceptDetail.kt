package com.example.cs330_pz05.domain.model

data class ReceptDetail(
    val difficulty: String,
    val id: String,
    val image: String,
    val kalorije: String,
    val nacinPripreme: String,
    val naziv: String,
    val sastojci: List<String>,
    val vreme_pripreme: String
) {


}