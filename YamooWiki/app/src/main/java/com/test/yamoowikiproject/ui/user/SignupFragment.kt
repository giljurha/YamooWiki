package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.ui.main.MainActivity


class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentSignupBinding.run {
            nextProfileBtn.setOnClickListener {
                val id = fragmentSignupBinding.userIdInput.text.toString()
                val nickName = fragmentSignupBinding.userNickNameInput.text.toString()
                val pw = fragmentSignupBinding.userPwInput.text.toString()
                val phoneNumber = fragmentSignupBinding.userPhoneInput.text.toString()

                val userModel = User(id,nickName,pw,phoneNumber)
                val bundle = Bundle()
                bundle.putParcelable("signInfo",userModel)

                mainActivity.replaceFragment(MainActivity.PROFILE_FRAGMENT,true,false,bundle)
            }
        }
        return fragmentSignupBinding.root
    }
}