package com.test.yamoowikiproject.ui.authentication.signup

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.db.UserDao
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.db.YamooWikiDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignupViewModel : ViewModel() {
    private val _isDuplicatedId = MutableLiveData<Boolean>()
    val isDuplicatedId: LiveData<Boolean>
        get() = _isDuplicatedId

    private val _isDuplicatedNickName = MutableLiveData<Boolean>()
    val isDuplicatedNickName: LiveData<Boolean>
        get() = _isDuplicatedNickName

    fun signup(userEntity: UserEntity, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()
                .insertUser(userEntity = userEntity)
        }
    }

    fun checkId(userId: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val existUserId: String? = YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()
                .getUserId(userId = userId)
            _isDuplicatedId.postValue(existUserId != null)
        }
    }

    fun checkNickName(userNickName: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val existUserNickName: String? = YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()
                .getUserNickName(userNickName = userNickName)
            _isDuplicatedNickName.postValue(existUserNickName != null)
        }
    }
}
