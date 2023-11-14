package com.example.database.di

import android.content.Context
import com.example.database.db.NYCDatabase
import com.example.database.db.satdata.SatDataDao
import com.example.database.db.schooldata.SchoolDataDao
import com.example.database.repository.RoomDBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideSchoolDataDao(@ApplicationContext context: Context): SchoolDataDao {
        return NYCDatabase.getInstance(context).schoolDataDao
    }

    @Provides
    fun provideSatDataDao(@ApplicationContext context: Context): SatDataDao {
        return NYCDatabase.getInstance(context).satDataDao
    }

    @Provides
    fun provideRoomDBRepository(
        schoolDataDao: SchoolDataDao,
        satDataDao: SatDataDao,
    ) = RoomDBRepository(
        schoolDataDao = schoolDataDao, satDataDao = satDataDao
    )
}
