package com.example.cs330_pz05.data.remote.dto

import com.example.cs330_pz05.domain.model.Recept
import com.example.cs330_pz05.domain.model.ReceptDetail

data class ReceptDto(
    val difficulty: String,
    val id: String,
    val image: String,
    val kalorije: String,
    val nacinPripreme: String,
    val naziv: String,
    val sastojci: List<String>,
    val vreme_pripreme: String
)

//mapiramo Dto u Recept koji ce dalje biti koriscen u Domain/Model
fun ReceptDto.toRecept(): Recept {
    return Recept(
        id=id,
        naziv = naziv,
        difficulty = difficulty,
        image = image,
        vreme_pripreme = vreme_pripreme,
        kalorije = kalorije
    )
}

fun ReceptDto.toReceptDetail(): ReceptDetail {
    return ReceptDetail(
        id=id,
        naziv = naziv,
        difficulty = difficulty,
        image = image,
        vreme_pripreme = vreme_pripreme,
        kalorije = kalorije,
        nacinPripreme = nacinPripreme,
        sastojci = sastojci
    )
}