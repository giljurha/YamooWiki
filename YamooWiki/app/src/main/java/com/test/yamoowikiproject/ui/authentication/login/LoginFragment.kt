package com.test.yamoowikiproject.ui.authentication.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.yamoowikiproject.BuildConfig
import com.test.yamoowikiproject.databinding.DialogErrorBinding
import com.test.yamoowikiproject.databinding.FragmentLoginBinding
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.retrofit.Address
import com.test.yamoowikiproject.retrofit.AddressService
import com.test.yamoowikiproject.retrofit.Document
import com.test.yamoowikiproject.retrofit.RetrofitConnection
import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.ui.authentication.model.AuthenticationFragmentType
import com.test.yamoowikiproject.ui.authentication.model.SignupErrorState
import com.test.yamoowikiproject.ui.authentication.AuthenticationViewModel
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    lateinit var intent: Intent
    private val authenticationViewModel: AuthenticationViewModel by activityViewModels()
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

        loginViewModel.isLogin.observe(viewLifecycleOwner) {
            if (it == true) {
                intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                loginViewModel.loginUserEntity.value?.let {
                    saveSharedPreferences(it, requireContext())
                }
            }
        }
        initViews()

        viewLifecycleOwner.lifecycleScope.launch {
            val addressData = RetrofitConnection.retrofit.create(AddressService::class.java).searchAddress(
                token = "KakaoAK " + BuildConfig.KAKAO_API_KEY,
                query = "종로"
            )
            val list = arrayListOf<Address>()
            addressData.documents.forEach { document: Document ->
                list.add(document.address)
            }
            Log.d("address","${addressData.documents[0].address.addressName}")
        }
    }

    private fun initViews() {
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
                loginViewModel.login(id, password, requireContext())
            }
            tvSignup.setOnClickListener {
                authenticationViewModel.changeAuthenticationFragmentType(authenticationFragmentType = AuthenticationFragmentType.SIGNUP)
            }
        }
    }

    private fun saveSharedPreferences(userEntity: UserEntity, context: Context) {
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
