package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    val userId = MutableLiveData<String>()
    val userNickName = MutableLiveData<String>()
    val userPw = MutableLiveData<String>()
    val userPwChk = MutableLiveData<String>()
    val userPhone = MutableLiveData<String>()
    val phoneCert = MutableLiveData<String>()
}