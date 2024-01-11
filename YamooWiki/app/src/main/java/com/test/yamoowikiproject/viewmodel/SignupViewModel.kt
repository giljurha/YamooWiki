package com.test.yamoowikiproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.repository.UserRepository

class SignupViewModel : ViewModel() {
    val userModelLiveData = MutableLiveData<User>()

    fun signUp(userModel: User){
        UserRepository.addUser(userModel){
            userModelLiveData.value = userModel
        }
    }
}