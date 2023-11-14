package com.example.database.repository

import com.example.database.db.satdata.SatData
import com.example.database.db.satdata.SatDataDao
import com.example.database.db.schooldata.SchoolData
import com.example.database.db.schooldata.SchoolDataDao
import javax.inject.Inject

class RoomDBRepository @Inject constructor(
    private val schoolDataDao: SchoolDataDao,
    private val satDataDao: SatDataDao,
) {
    fun deleteAllSchoolData() = schoolDataDao.deleteRecords()
    fun deleteAllSatData() = satDataDao.deleteRecords()

    fun getAllSchoolData() = schoolDataDao.getAll()
    fun getAllSatData() = satDataDao.getAll()

    fun getSchoolDataByDbn(dbn: String) = schoolDataDao.get(dbn)
    fun getSatDataByDbn(dbn: String) = satDataDao.get(dbn)

    suspend fun insertSchoolData(schoolData: List<SchoolData>) = schoolDataDao.insertAll(schoolData)
    suspend fun insertSatData(satData: List<SatData>) = satDataDao.insertAll(satData)
}