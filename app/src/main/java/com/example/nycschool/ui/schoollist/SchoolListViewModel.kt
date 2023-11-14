package com.example.nycschool.ui.schoollist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.network.SatData
import com.example.data.network.SchoolData
import com.example.database.repository.RoomDBRepository
import com.example.network.remote.RemoteRepository
import com.example.nycschool.ui.BaseViewModel
import com.example.nycschool.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolListViewModel @Inject constructor(
    private val roomDBRepository: RoomDBRepository, private val remoteRepository: RemoteRepository
) : BaseViewModel() {

    private val _schoolList: MutableLiveData<List<SchoolData>> = MutableLiveData()
    val schoolList: LiveData<List<SchoolData>> = _schoolList

    private val _schoolDataReceived: MutableLiveData<Boolean> = SingleLiveEvent()
    val schoolDataReceived: LiveData<Boolean> = _schoolDataReceived

    private val _satDataReceived: MutableLiveData<Boolean> = SingleLiveEvent()
    val satDataReceived: LiveData<Boolean> = _satDataReceived

    private val _isLoading: MutableLiveData<Boolean> = SingleLiveEvent()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _currentStatus: MutableLiveData<String> = MutableLiveData()
    val currentStatus: LiveData<String> = _currentStatus

    fun fetchSchoolData() {
        _isLoading.value = true
        viewModelScope.launch {
            async {
                remoteRepository.getSchoolList().collectLatest {
                    it.data?.let {
                        updateSchoolDataIntoDatabase(it)
                        _isLoading.value = false
                    }
                }
            }.await()
        }
    }

    fun fetchSatData() {
        _isLoading.value = true
        viewModelScope.launch {
            async {
                remoteRepository.getSatData().collectLatest {
                    it.data?.let {
                        updateSatDataIntoDatabase(it)
                        _isLoading.value = false
                    }
                }
            }.await()
        }
    }

    private fun updateSchoolDataIntoDatabase(schoolList: List<SchoolData>) {
        viewModelScope.launch {
            _currentStatus.value = "Encrypting and Saving school data to database"
            schoolList.map {
                com.example.database.db.schooldata.SchoolData.getFromNetworkModel(
                    it
                )
            }.apply {
                roomDBRepository.deleteAllSchoolData()
                roomDBRepository.insertSchoolData(this)
                _schoolDataReceived.value = true
            }
        }
    }

    private fun updateSatDataIntoDatabase(satData: List<SatData>) {
        viewModelScope.launch {
            _currentStatus.value = "Encrypting and Saving SAT data to database"
            satData.map {
                com.example.database.db.satdata.SatData.getFromNetworkModel(
                    it
                )
            }.apply {
                roomDBRepository.deleteAllSatData()
                roomDBRepository.insertSatData(this)
                _satDataReceived.value = true
            }
        }
    }

    fun readSchoolDataFromDatabase() {
        _isLoading.value = true
        viewModelScope.launch {
            _schoolList.value = roomDBRepository.getAllSchoolData().map {
                SchoolData(
                    dbn = it.dbn, schoolName = it.schoolName
                )
            }
            _isLoading.value = false
        }
    }
}