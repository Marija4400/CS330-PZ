package com.example.cs330_pz05.domain.use_case.get_by_sastojak

import com.example.cs330_pz05.common.Resource
import com.example.cs330_pz05.data.remote.dto.toRecept
import com.example.cs330_pz05.domain.model.Recept
import com.example.cs330_pz05.domain.repository.ReceptRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetReceptBySastojakUseCase @Inject constructor(
    private val repository: ReceptRepository
){

    operator fun invoke(sastojak: String): Flow<Resource<List<Recept>>> = flow{
        try {
            emit(Resource.Loading<List<Recept>>())
            val oznaceni = repository.getReceptBySastojak(sastojak).map {
                it.toRecept()
            }
            emit(Resource.Success<List<Recept>>(oznaceni))
        }
        catch (e: HttpException){
            emit(
                Resource.Error<List<Recept>>(
                    e.localizedMessage?: "Greska"
                )
            )
        }
        catch (e:IOException){
            Resource.Error<List<Recept>>(
                e.localizedMessage?: "Problem sa serverom"
            )
        }
    }
}