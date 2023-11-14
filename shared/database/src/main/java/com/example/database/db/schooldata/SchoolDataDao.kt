package com.example.database.db.schooldata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SchoolDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(satData: SchoolData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(satData: List<SchoolData>): List<Long>

    @Query("SELECT a.* FROM school_data a, sat_data b where a.dbn = b.dbn")
    fun getAll(): MutableList<SchoolData>

    @Query("SELECT * FROM school_data WHERE dbn = :dbn")
    fun get(dbn: String): SchoolData

    @Query("DELETE FROM school_data")
    fun deleteRecords()
}