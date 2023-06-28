package com.example.cs330_pz05.data.repository

import com.example.cs330_pz05.data.remote.ReceptApi
import com.example.cs330_pz05.data.remote.dto.ReceptDto
import com.example.cs330_pz05.domain.model.ReceptDetail
import com.example.cs330_pz05.domain.repository.ReceptRepository
import javax.inject.Inject

class ReceptRepositoryImpl @Inject constructor(
    private val api: ReceptApi
) : ReceptRepository {
    override suspend fun getAllRecept(): List<ReceptDto> {
        return api.getAllRecept()
    }

    override suspend fun getReceptById(receptId: String): ReceptDto {
        return api.getReceptById(receptId)
    }

    override suspend fun getReceptBySastojak(sastojak: String): List<ReceptDto> {
        return api.getReceptBySastojak(sastojak)
    }

}