package com.test.yamoowikiproject.ui.user

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding

    lateinit var signupViewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        signupViewModel = ViewModelProvider(this)[SignupViewModel::class.java]

        signupViewModel.run {
            userId.observe(viewLifecycleOwner) {
                fragmentSignupBinding.userIdInput.setText(it)
            }
            userNickName.observe(viewLifecycleOwner) {
                fragmentSignupBinding.userNickNameInput.setText(it)
            }
            userPw.observe(viewLifecycleOwner) {
                fragmentSignupBinding.userPwInput.setText(it)
            }
            userPwChk.observe(viewLifecycleOwner) {
                fragmentSignupBinding.userPwChkInput.setText(it)
            }
            userPhone.observe(viewLifecycleOwner) {
                fragmentSignupBinding.userPhoneInput.setText(it)
            }
            userEmail.observe(viewLifecycleOwner) {
                fragmentSignupBinding.userEmailInput.setText(it)
            }
        }

        fragmentSignupBinding.run {
            val id = userIdInput.text.toString()
            val nickName = userNickNameInput.text.toString()
            val pw = userPwInput.text.toString()
            val phoneNumber = userPhoneInput.text.toString()

            nextProfileBtn.setOnClickListener {

                val userModel = User(id, nickName, pw, phoneNumber)
                val bundle = Bundle()
                bundle.putParcelable("signInfo", userModel)

               replaceFragment(ProfileFragment())
            }
        }
        return fragmentSignupBinding.root
    }


    fun chkError() {
        fragmentSignupBinding.run {
            val id = userIdInput.text.toString()
            val nickName = userNickNameInput.text.toString()
            val pw = userPwInput.text.toString()
            val pwChk = userPwChkInput.text.toString()
            val phoneNumber = userPhoneInput.text.toString()

            if (pw != pwChk) {
                val builder = MaterialAlertDialogBuilder(requireContext())
                builder.setTitle("비밀번호 오류")
                builder.setMessage("비밀번호가 일치하지 않습니다.")
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                }
                builder.show()
            }
        }

    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContainer, fragment)
        fragmentTransaction.commit()
    }

//    fun replaceFragment(name: String, addToBackStack: Boolean){
//        val fragmentTransaction = parentFragmentManager.beginTransaction()
//
//        val fragment = when(name){
//            LOGIN_FRAGMENT -> LoginFragment()
//            SIGNUP_FRAGMENT -> SignupFragment()
//            PROFILE_FRAGMENT -> ProfileFragment()
//            else -> Fragment()
//        }
//
//        fragmentTransaction.replace(R.id.mainContainer, fragment)
//
//        if (addToBackStack) fragmentTransaction.addToBackStack(name)
//        fragmentTransaction.commit()
//    }

}

