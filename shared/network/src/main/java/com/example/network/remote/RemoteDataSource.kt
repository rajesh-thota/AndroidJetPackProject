package com.example.network.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val nycService: NycService
) {
    suspend fun getSchoolList() = nycService.getSchoolList()
    suspend fun getSatData() = nycService.getSatData()
}
