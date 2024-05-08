package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.ui.user.AuthenticationFragmentType

class AuthenticationViewModel: ViewModel() {

    private val _authenticationFragmentType = MutableLiveData<AuthenticationFragmentType>()
    val authenticationFragmentType: LiveData<AuthenticationFragmentType>
        get() = _authenticationFragmentType

    fun changeAuthenticationFragmentType(authenticationFragmentType: AuthenticationFragmentType) {
        _authenticationFragmentType.value = authenticationFragmentType
    }

}