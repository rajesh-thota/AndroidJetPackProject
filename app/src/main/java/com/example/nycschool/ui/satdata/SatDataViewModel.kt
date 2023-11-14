package com.example.nycschool.ui.satdata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.network.SatData
import com.example.database.repository.RoomDBRepository
import com.example.nycschool.ui.BaseViewModel
import com.example.nycschool.util.SingleLiveEvent
import com.example.security.decrypt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatDataViewModel @Inject constructor(
    private val roomDBRepository: RoomDBRepository,
) : BaseViewModel() {

    private val _satData: MutableLiveData<SatData> = SingleLiveEvent()
    val satData: LiveData<SatData> = _satData

    fun readSatDataFromDatabase(dbn: String) {
        viewModelScope.launch {
            val data = roomDBRepository.getSatDataByDbn(dbn)
            data?.let {
                _satData.value = SatData(
                    dbn = data.dbn,
                    schoolName = data.schoolName,
                    satCriticalReadingAvgScore = data.satCriticalReadingAvgScore.decrypt(),
                    satMathAvgScore = data.satMathAvgScore.decrypt(),
                    satWritingAvgScore = data.satWritingAvgScore.decrypt(),
                )
            }
        }
    }
}