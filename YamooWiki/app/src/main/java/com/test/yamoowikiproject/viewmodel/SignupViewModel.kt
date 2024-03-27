package com.test.yamoowikiproject.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.db.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class SignupViewModel : ViewModel() {
    val userModelLiveData = MutableLiveData<User>()

    fun signup(user: UserEntity, context: Context) {

        CoroutineScope(kotlinx.coroutines.Dispatchers.IO).launch {
            com.test.yamoowikiproject.db.YamooWikiDatabase.getInstance(context).getUserDao()
                .insertUser(user)
        }

    }
}
