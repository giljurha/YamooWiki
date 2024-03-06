package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentLoginBinding
import com.test.yamoowikiproject.db.UserDao
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.db.YamooWikiDatabase
import com.test.yamoowikiproject.ui.home.HomeFragment
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginFragment : Fragment() {
    lateinit var fragmentLoginBinding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        return fragmentLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.isVisibleBottomNavigationView.value = false
        login()

        fragmentLoginBinding.run {
            signupText.setOnClickListener {
                mainViewModel.fragmentDestination.value = FragmentType.SIGNUP
            }
            loginButton.setOnClickListener {
                mainViewModel.fragmentDestination.value = FragmentType.LOGIN
            }
        }
    }

    fun login() {
        val id: String = fragmentLoginBinding.idInput.toString()
        val password: String = fragmentLoginBinding.passwordInput.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val userEntity =
                YamooWikiDatabase.getInstance(requireContext()).getUserDao()
                    .getUserInfo(id, password)

            withContext(Dispatchers.Main) {
                if (userEntity != null) {
                    mainViewModel.fragmentDestination.value = FragmentType.HOME
                    Toast.makeText(requireContext(), "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
