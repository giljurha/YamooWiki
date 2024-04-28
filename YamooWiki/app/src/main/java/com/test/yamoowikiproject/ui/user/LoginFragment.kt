package com.test.yamoowikiproject.ui.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.yamoowikiproject.databinding.DialogErrorBinding
import com.test.yamoowikiproject.databinding.FragmentLoginBinding
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.ui.user.model.SignupErrorState
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

        mainViewModel.changeStateBottomNavigaitonView(fragmentType = FragmentType.LOGIN)
        loginViewModel.isLogin.observe(viewLifecycleOwner) {
            if (it == true) {
                mainViewModel.changeFragmentType(fragmentType = FragmentType.HOME)
                mainViewModel.changeStateBottomNavigaitonView(fragmentType = FragmentType.HOME)
                loginViewModel.loginUserEntity.value?.let {
                    saveSharedPreferences(it, requireContext())
                }
            }
        }
        initViews()

    }

    fun initViews() {
        with(binding) {
            btnLogin.setOnClickListener {
                val id = binding.etUserId.text.toString()
                val password = binding.etUserPassword.text.toString()
                if (id.isEmpty()) {
                    showDialog(SignupErrorState.ID)
                } else if (password.isEmpty()) {
                    showDialog(SignupErrorState.PASSWORD)
                } else {
                    loginViewModel.login(id, password, requireContext())
                }
            }
            tvSignup.setOnClickListener {
                mainViewModel.changeFragmentType(fragmentType = FragmentType.SIGNUP)
            }
        }
    }

    fun saveSharedPreferences(userEntity: UserEntity, context: Context) {
        context
            .getSharedPreferences("loginUser", Context.MODE_PRIVATE)
            .edit()
            .putString("userId", userEntity.userId)
            .putString("userImageUri", userEntity.userImageUri)
            .commit()
    }

    private fun showDialog(signupErrorState: SignupErrorState) {

        val focusView: View? = when (signupErrorState) {
            SignupErrorState.ID -> binding.etUserId
            SignupErrorState.PASSWORD -> binding.etUserPassword
            SignupErrorState.DUPLICATEDID -> binding.etUserId
            SignupErrorState.NOTERROR -> null
            SignupErrorState.NICKNAME -> null
            SignupErrorState.PROFILE -> null
            SignupErrorState.DUPLICATEDNICKNAME -> null
        }

        MaterialAlertDialogBuilder(requireContext()).run {
            val dialogErrorBinding: DialogErrorBinding = DialogErrorBinding.inflate(layoutInflater)
            dialogErrorBinding.textView.text = signupErrorState.message
            setView(dialogErrorBinding.root)
            setPositiveButton("확인") { dialog, which ->
                focusView?.requestFocus()
            }
            setCancelable(true)
            show()
        }.setCanceledOnTouchOutside(true)
    }
}
