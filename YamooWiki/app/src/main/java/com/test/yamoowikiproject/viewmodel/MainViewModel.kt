package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.ui.main.FragmentType


class MainViewModel : ViewModel() {
    val isVisibleBottomNavigationView = MutableLiveData<Boolean>()
    val fragmentDestination = MutableLiveData<FragmentType>()
    var userModel: User? = null
}