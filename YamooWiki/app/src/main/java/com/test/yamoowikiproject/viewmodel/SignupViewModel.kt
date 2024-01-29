package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.dataclassmodel.User


class SignupViewModel : ViewModel() {
    val userModelLiveData = MutableLiveData<User>()

    val userId = MutableLiveData<String>()
    val userNickName = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()
    val userPwChk = MutableLiveData<String>()
    val userPhone = MutableLiveData<String>()
    val userEmail = MutableLiveData<String>()


}