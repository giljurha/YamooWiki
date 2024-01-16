package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    lateinit var mainActivity: MainActivity
    lateinit var signupViewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        signupViewModel = ViewModelProvider(mainActivity)[SignupViewModel::class.java]

        signupViewModel.run{
            userId.observe(mainActivity){
                fragmentSignupBinding.userIdInput.setText(it)
            }
            userNickName.observe(mainActivity){
                fragmentSignupBinding.userNickNameInput.setText(it)
            }
            userPw.observe(mainActivity){
                fragmentSignupBinding.userPwInput.setText(it)
            }
            userPwChk.observe(mainActivity){
                fragmentSignupBinding.userPwChkInput.setText(it)
            }
            userPhone.observe(mainActivity){
                fragmentSignupBinding.userPhoneInput.setText(it)
            }
            userPhoneCert.observe(mainActivity){
                fragmentSignupBinding.userPhoneCertInput.setText(it)
            }
        }

        fragmentSignupBinding.run {

            nextProfileBtn.setOnClickListener {
                val id = userIdInput.text.toString()
                val nickName = userNickNameInput.text.toString()
                val pw = userPwInput.text.toString()
                val phoneNumber = userPhoneInput.text.toString()

                val userModel = User(id,nickName,pw,phoneNumber)
                val bundle = Bundle()
                bundle.putParcelable("signInfo",userModel)

                mainActivity.replaceFragment(MainActivity.PROFILE_FRAGMENT,true,false,bundle)
            }
        }
        return fragmentSignupBinding.root
    }
}