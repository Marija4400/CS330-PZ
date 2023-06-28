package com.example.cs330_pz05.domain.model

//ova klasa se koristi da prikazemo podatke koje zelimo
data class Recept(
    val difficulty: String,
    val id: String,
    val image: String,
    val naziv: String,
    val vreme_pripreme: String,
    val kalorije: String
)
