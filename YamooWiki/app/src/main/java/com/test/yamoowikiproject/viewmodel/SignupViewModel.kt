package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.dataclassmodel.User


class SignupViewModel : ViewModel() {
    val userModelLiveData = MutableLiveData<User>()
}
