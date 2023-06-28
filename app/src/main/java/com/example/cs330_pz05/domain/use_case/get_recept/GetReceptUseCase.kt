package com.example.cs330_pz05.domain.use_case.get_recept

import com.example.cs330_pz05.common.Resource
import com.example.cs330_pz05.data.remote.dto.toRecept
import com.example.cs330_pz05.data.remote.dto.toReceptDetail
import com.example.cs330_pz05.domain.model.Recept
import com.example.cs330_pz05.domain.model.ReceptDetail
import com.example.cs330_pz05.domain.repository.ReceptRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetReceptUseCase @Inject constructor(
    private val repository: ReceptRepository
) {
    operator fun invoke(receptId: String): Flow<Resource<ReceptDetail>> = flow {
        try {
            emit(Resource.Loading())
            val recept = repository.getReceptById(receptId).toReceptDetail()
            emit(Resource.Success(recept))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?: "Doslo je do greske!"))

        }catch (e:IOException){
            emit(Resource.Error(e.localizedMessage?:"Problem sa serverom, proverite Internet konekciju"))

        }
    }
}