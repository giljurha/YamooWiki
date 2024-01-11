package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.yamoowikiproject.databinding.FragmentProfileBinding
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.repository.UserRepository
import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class ProfileFragment : Fragment() {
    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var signupViewModel: SignupViewModel
    private var userModel: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mainActivity = activity as MainActivity
        fragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
        signupViewModel = ViewModelProvider(mainActivity)[SignupViewModel::class.java]

        userModel = arguments?.getParcelable("signInfo")
        Log.d("testt", "$userModel")

        //회원가입 결과를 받기 위한 옵저버
        signupViewModel.run{
            userModelLiveData.observe(mainActivity){
                mainActivity.removeFragment(MainActivity.SINGUP_FRAGMENT)
                mainActivity.removeFragment(MainActivity.LOGIN_FRAGMENT)
                mainActivity.removeFragment(MainActivity.PROFILE_FRAGMENT)
                mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, false, null)
            }
        }

        fragmentProfileBinding.run {
            signUpbutton.setOnClickListener {
                if (userModel == null){
                    Toast.makeText(this@ProfileFragment.context,"회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show()
                }else{
                    signupViewModel.signUp(userModel!!)
                }
            }
        }
        return fragmentProfileBinding.root
    }


}