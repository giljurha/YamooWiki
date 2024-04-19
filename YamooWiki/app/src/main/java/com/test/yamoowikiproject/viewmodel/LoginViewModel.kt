package com.test.yamoowikiproject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.db.YamooWikiDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean>
        get() = _isLogin


    fun login(userId: String, userPassword: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val userIdPassword: UserEntity? = YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()
                .getUserByIdPassword(userId = userId, userPassword = userPassword)
            _isLogin.postValue(userIdPassword != null)
        }
    }
}
