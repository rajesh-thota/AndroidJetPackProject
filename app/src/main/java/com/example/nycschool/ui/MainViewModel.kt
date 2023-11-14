package com.example.nycschool.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {
    private val _toolbarVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val toolbarVisibility: LiveData<Boolean> = _toolbarVisibility

    private val _toolbarText: MutableLiveData<String> = MutableLiveData()
    val toolbarText: LiveData<String> = _toolbarText

    private val _toolbarBackEnabled: MutableLiveData<Boolean> = MutableLiveData()
    val toolbarBackEnabled: LiveData<Boolean> = _toolbarBackEnabled

    private val _currentStatus: MutableLiveData<String> = MutableLiveData()
    val currentStatus: LiveData<String> = _currentStatus

    fun hideActionBar() {
        _toolbarVisibility.value = false
    }

    fun showActionBar() {
        _toolbarVisibility.value = true
    }

    fun setTitle(title: String) {
        _toolbarText.value = title
    }

    fun hideActionBarBack() {
        _toolbarBackEnabled.value = false
    }

    fun showActionBarBack() {
        _toolbarBackEnabled.value = true
    }

    fun showCurrentStatus(currentStatus: String?) {
        _currentStatus.value = currentStatus.orEmpty()
    }
}