package com.test.yamoowikiproject.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.yamoowikiproject.databinding.FragmentLoginBinding
import com.test.yamoowikiproject.db.YamooWikiDatabase
import com.test.yamoowikiproject.ui.main.FragmentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>()
    val isLogin: LiveData<Boolean>
        get() = _isLogin

    fun login(id: String, password: String, context: Context) {

        CoroutineScope(Dispatchers.IO).launch {
            val userEntity = YamooWikiDatabase.getInstance(context)
                .getUserDao()
                .getUserInfo(id, password)

            _isLogin.value = userEntity != null
        }
    }
}