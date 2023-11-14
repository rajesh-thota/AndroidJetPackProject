package com.example.network.remote

import com.example.data.network.SatData
import com.example.data.network.SchoolData
import com.example.network.model.BaseApiResponse
import com.example.network.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class RemoteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : BaseApiResponse() {
    fun getSchoolList(): Flow<NetworkResult<List<SchoolData>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getSchoolList() })
        }.flowOn(Dispatchers.IO)
    }

    fun getSatData(): Flow<NetworkResult<List<SatData>>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getSatData() })
        }.flowOn(Dispatchers.IO)
    }
}
