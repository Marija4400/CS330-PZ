package com.example.cs330_pz05.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.cs330_pz05.common.Constants
import com.example.cs330_pz05.data.database.NamirniceDatabase
import com.example.cs330_pz05.data.database.dao.NamirniceDao
import com.example.cs330_pz05.data.remote.ReceptApi
import com.example.cs330_pz05.data.repository.ReceptRepositoryImpl
import com.example.cs330_pz05.domain.repository.ReceptRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideReceptApi(): ReceptApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReceptApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReceptRepository(api:ReceptApi): ReceptRepository{
        return ReceptRepositoryImpl(api)
    }




    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideReceptDetailDatabase(context: Context): NamirniceDatabase {
        return Room.databaseBuilder(
            context,
            NamirniceDatabase::class.java,
            "anime_detail_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideREceptDetailDao(database: NamirniceDatabase): NamirniceDao {
        return database.dao
    }




}