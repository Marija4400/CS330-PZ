package com.example.cs330_pz05.data.database

import com.example.cs330_pz05.domain.model.Namirnice

sealed interface NamirniceEvent {
    object SaveNamirnice: NamirniceEvent
    data class SetTipProdavnice(val tipProdavnice:String): NamirniceEvent
    data class SetNaziv(val naziv: String): NamirniceEvent
    data class SetKolicina(val kolicina: String): NamirniceEvent
    object ShowDialog: NamirniceEvent
    object HideDialog: NamirniceEvent
    data class SortNamirnice(val sortType: SortType): NamirniceEvent
    data class DeleteNamirnice(val namirnice: Namirnice):NamirniceEvent
}