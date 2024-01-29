package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val isVisibleBottomNavigationView = MutableLiveData<Boolean>()
}