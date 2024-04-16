package com.test.yamoowikiproject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.yamoowikiproject.db.YamooWikiDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean>
        get() = _isLogin

//    val user: UserEntity? by lazy {
//
//    }



    fun login(id: String, password: String, context: Context) {
        viewModelScope
        CoroutineScope(Dispatchers.IO).launch {

            val userEntity = YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()

            val userId = userEntity.getUserId(userId = id)
            val userIdPassword = userEntity.getUserInfo(userId = id, password = password)

            if (userId != null) _isLogin.postValue(userIdPassword != null)

        }
    }

    fun checkId(id: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val userEntity = YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()
                .getUserId(userId = id)
        }
    }
}

