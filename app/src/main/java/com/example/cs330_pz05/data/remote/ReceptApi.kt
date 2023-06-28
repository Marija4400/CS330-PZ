package com.example.cs330_pz05.data.remote

import com.example.cs330_pz05.data.remote.dto.ReceptDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//definisemo cemu zelimo da pristupimo sa API-a

interface ReceptApi {

    @GET("/recept")
    suspend fun getAllRecept(): List<ReceptDto>

     @GET("/recept/{receptId}")
    suspend fun getReceptById(@Path("receptId") receptId: String): ReceptDto

    @GET("/recept")
    suspend fun getReceptBySastojak(
        @Query("sastojci_like") sastojak: String,
        @Query("title_regex_flags") flags: String = "i"
    ):List<ReceptDto>

}