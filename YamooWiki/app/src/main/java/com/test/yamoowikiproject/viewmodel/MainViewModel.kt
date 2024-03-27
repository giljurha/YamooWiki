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


    fun changeStateBottomNavigaitonView(){
        _isVisibleBottomNavigationView.value = false
    }

    fun changeFragmentType(fragmentName: String) {
        when (fragmentName) {
            "Login" -> _fragmentDestination.value = FragmentType.LOGIN
            "Signup" -> _fragmentDestination.value = FragmentType.SIGNUP
            "Home" -> _fragmentDestination.value = FragmentType.HOME
            "Search" -> _fragmentDestination.value = FragmentType.SEARCH
            "MyInfo" -> _fragmentDestination.value = FragmentType.MYINFO
        }
    }
}
