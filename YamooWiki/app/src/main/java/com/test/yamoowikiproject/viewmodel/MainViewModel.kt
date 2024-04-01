package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.ui.main.FragmentType


class MainViewModel : ViewModel() {
    private val _isVisibleBottomNavigationView = MutableLiveData<Boolean>()
    val isVisibleBottomNavigationView: LiveData<Boolean>
        get() = _isVisibleBottomNavigationView

    private val _fragmentDestination = MutableLiveData<FragmentType>()
    val fragmentDestination: LiveData<FragmentType>
        get() = _fragmentDestination


    fun changeStateBottomNavigaitonView() {
        if (_isVisibleBottomNavigationView.value == true) {
            _isVisibleBottomNavigationView.value = false
        } else {
            _isVisibleBottomNavigationView.value = true
        }
    }

    fun changeFragmentType(fragmentType: FragmentType) {
        _fragmentDestination.value = fragmentType
    }
}
