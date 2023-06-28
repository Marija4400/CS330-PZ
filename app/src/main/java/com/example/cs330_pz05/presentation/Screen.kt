package com.example.cs330_pz05.presentation

sealed class Screen (val route: String){
    object ReceptListScreen: Screen("recept_list_screen")
    object ReceptDetailScreen: Screen("recept_detail_screen")
    object CheckedSastojakScreen: Screen("checked_sastojak_screen")
    object NamirniceScreen: Screen("namirnice_screen")
    object PretragaScreen: Screen("pretraga_screen")
    object PocetnaScreen:Screen("pocetna_screen")
}