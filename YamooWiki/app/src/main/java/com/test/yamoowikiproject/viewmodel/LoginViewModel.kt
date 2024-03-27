package com.test.yamoowikiproject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
            val userEntity = YamooWikiDatabase.getInstance(context)
                .getUserDao()
                .getUserInfo(id, password)

            _isLogin.postValue(userEntity != null)
        }
    }
}
