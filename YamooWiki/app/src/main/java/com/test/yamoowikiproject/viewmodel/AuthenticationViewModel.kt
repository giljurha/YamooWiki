package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.ui.user.LoginFragmentType

class AuthenticationViewModel: ViewModel() {

    private val _loginFragment = MutableLiveData<LoginFragmentType>()
    val loginFragment: LiveData<LoginFragmentType>
        get() = _loginFragment

    fun loginFragment(loginFragmentType: LoginFragmentType) {
        _loginFragment.value = loginFragmentType
    }

}