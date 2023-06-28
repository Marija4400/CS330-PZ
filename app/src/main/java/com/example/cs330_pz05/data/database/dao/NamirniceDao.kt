package com.example.cs330_pz05.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.cs330_pz05.domain.model.Namirnice
import kotlinx.coroutines.flow.Flow

@Dao
interface NamirniceDao {

    @Upsert
    suspend fun upsertNamirnice(namirnice: Namirnice)

    @Delete
    suspend fun deleteNamirnice(namirnice: Namirnice)

    @Query("SELECT * FROM namirnice ORDER BY naziv ASC")
    fun getNamirniceOrderedByNaziv(): Flow<List<Namirnice>>

    @Query("SELECT * FROM namirnice ORDER BY tipProdavnice ASC")
    fun getNamirniceOrderedByTipProdavnice(): Flow<List<Namirnice>>
}