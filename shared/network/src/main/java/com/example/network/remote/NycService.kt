package com.example.network.remote

import com.example.data.network.SatData
import com.example.data.network.SchoolData
import com.example.network.utils.Constants.Companion.SAT_DATA
import com.example.network.utils.Constants.Companion.SCHOOL_LIST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface NycService {

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET(SCHOOL_LIST)
    suspend fun getSchoolList(): Response<List<SchoolData>>

    @Headers("Content-Type: application/json;charset=UTF-8")
    @GET(SAT_DATA)
    suspend fun getSatData(): Response<List<SatData>>
}
