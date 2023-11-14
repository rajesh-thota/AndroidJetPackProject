package com.example.database.db.satdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SatDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(satData: SatData): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(satData: List<SatData>): List<Long>

    @Query("SELECT * FROM sat_data")
    fun getAll(): MutableList<SatData>

    @Query("SELECT * FROM sat_data WHERE dbn = :dbn")
    fun get(dbn: String): SatData?

    @Query("DELETE FROM sat_data")
    fun deleteRecords()
}