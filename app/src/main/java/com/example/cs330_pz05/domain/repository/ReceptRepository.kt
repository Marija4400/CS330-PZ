package com.example.cs330_pz05.domain.repository

import com.example.cs330_pz05.data.remote.dto.ReceptDto
import com.example.cs330_pz05.domain.model.ReceptDetail

//u ovom delu se samo definise repository, dok se u data/repository implementira

interface ReceptRepository {

    suspend fun getAllRecept(): List<ReceptDto>

    suspend fun getReceptById(receptId: String):ReceptDto

    suspend fun getReceptBySastojak(sastojak: String): List<ReceptDto>


}