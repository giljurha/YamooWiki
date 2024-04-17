package com.test.yamoowikiproject.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.db.UserDao
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.db.YamooWikiDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SignupViewModel : ViewModel() {
    /* TODO: glide에서 처리한 이미지를 db에 저장하는 값을 저장 */

    private val _isDuplicatedId = MutableLiveData<Boolean>()
    val isDuplicatedId: LiveData<Boolean>
        get() = _isDuplicatedId

    private val _isDuplicatedNickName = MutableLiveData<Boolean>()
    val isDuplicatedNickName: LiveData<Boolean>
        get() = _isDuplicatedNickName


    fun signup(userEntity: UserEntity, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {

            // 중복된 id나 닉네임이 없을 경우 회원가입 진행
//            userDao.insertUser(userEntity = userEntity)
            YamooWikiDatabase
                .getInstance(context = context)
                .getUserDao()
                .insertUser(userEntity = userEntity)
        }
    }

    fun checkId(userId: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDao: UserDao = YamooWikiDatabase.getInstance(context = context).getUserDao()
            val duplicateUser: UserEntity? = userDao.getUserId(userId = userId)
            _isDuplicatedId.postValue(duplicateUser != null)
        }
    }

    fun checkNickName(userNickName: String, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val userDao = YamooWikiDatabase.getInstance(context = context).getUserDao()
            val duplicateNickName = userDao.getUserNickname(userNickName = userNickName)
            _isDuplicatedNickName.postValue(duplicateNickName == null)
        }
    }

}
