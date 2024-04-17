package com.test.yamoowikiproject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.db.UserDao
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.db.YamooWikiDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean>
        get() = _isLogin


    fun login(id: String, password: String, context: Context) {

        CoroutineScope(Dispatchers.IO).launch {

            val userDao: UserDao = YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()

            val userId: UserEntity? = userDao.getUserId(userId = id)
            val userIdPassword: UserEntity? = userDao.getUserIdPassword(userId = id, password = password)

            if (userId != null) _isLogin.postValue(userIdPassword != null)

        }
    }

    fun checkId(id: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val userEntity: UserEntity? = YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()
                .getUserId(userId = id)
        }
    }
}
