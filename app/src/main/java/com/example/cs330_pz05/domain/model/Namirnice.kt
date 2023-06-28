package com.example.cs330_pz05.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Namirnice(
    val tipProdavnice: String,
    val naziv: String,
    val kolicina: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)