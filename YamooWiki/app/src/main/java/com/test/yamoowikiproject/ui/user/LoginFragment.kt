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
    lateinit var binding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = binding.etId.text.toString()
        val password = binding.etPassword.text.toString()

        mainViewModel.changeStateBottomNavigaitonView(fragmentType = FragmentType.LOGIN)
        loginViewModel.isLogin.observe(viewLifecycleOwner) {
            if (it == true) {
                mainViewModel.changeFragmentType(fragmentType = FragmentType.HOME)
            }
        }

        with(binding) {
            btnLogin.setOnClickListener {
                loginViewModel.login(id, password, requireContext())
                /* TODO: 정상적으로 로그인 되었을 때만 아래에 수행 */
                mainViewModel.changeFragmentType(fragmentType = FragmentType.HOME)
                mainViewModel.changeStateBottomNavigaitonView(fragmentType = FragmentType.HOME)
                Toast.makeText(context, "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show()
            }
            tvSignup.setOnClickListener {
                mainViewModel.changeFragmentType(fragmentType = FragmentType.SIGNUP)
            }
        }
    }
}
