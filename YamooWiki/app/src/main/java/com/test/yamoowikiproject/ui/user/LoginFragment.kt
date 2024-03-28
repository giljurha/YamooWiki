package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.test.yamoowikiproject.databinding.FragmentLoginBinding
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.viewmodel.LoginViewModel
import com.test.yamoowikiproject.viewmodel.MainViewModel


class LoginFragment : Fragment() {
    lateinit var fragmentLoginBinding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        return fragmentLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.changeStateBottomNavigaitonView()
        loginViewModel.isLogin.observe(viewLifecycleOwner) {
            if (it == true) {
                mainViewModel.changeFragmentType(FragmentType.HOME)
            }
        }

        fragmentLoginBinding.run {
            loginButton.setOnClickListener {
                val id = fragmentLoginBinding.idInput.text.toString()
                val password = fragmentLoginBinding.passwordInput.text.toString()
                loginViewModel.login(id, password, requireContext())
                /* TODO: 정상적으로 로그인 되었을 때만 아래에 수행 */
                mainViewModel.changeFragmentType(FragmentType.HOME)
                Toast.makeText(context, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
            }
            signupText.setOnClickListener {
                mainViewModel.changeFragmentType(FragmentType.SIGNUP)
            }
        }
    }
}
