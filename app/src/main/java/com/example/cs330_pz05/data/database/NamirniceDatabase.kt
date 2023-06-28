package com.example.cs330_pz05.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cs330_pz05.data.database.dao.NamirniceDao
import com.example.cs330_pz05.domain.model.Namirnice

@Database(
    entities = [Namirnice::class],
    version = 1
)


abstract class NamirniceDatabase: RoomDatabase() {

    abstract val dao: NamirniceDao
}