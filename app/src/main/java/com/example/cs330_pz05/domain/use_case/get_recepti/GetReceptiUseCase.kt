package com.example.cs330_pz05.domain.use_case.get_recepti

import com.example.cs330_pz05.common.Resource
import com.example.cs330_pz05.data.remote.dto.toRecept
import com.example.cs330_pz05.domain.model.Recept
import com.example.cs330_pz05.domain.repository.ReceptRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

//injektuejmo repository
class GetReceptiUseCase @Inject constructor(
    private val repository: ReceptRepository
) {
    //koristim Flow zato sto zelim da emitujem vise vrednosti za odredjeni period
    operator fun invoke(): Flow<Resource<List<Recept>>> = flow {
        try {
            emit(Resource.Loading())
            val recepti = repository.getAllRecept().map { it.toRecept() }
            emit(Resource.Success(recepti))
        }catch (e:HttpException){
            emit(Resource.Error(e.localizedMessage?: "Doslo je do greske!"))

        }catch (e:IOException){
            emit(Resource.Error(e.localizedMessage?:"Problem sa serverom, proverite Internet konekciju"))

        }
    }
}