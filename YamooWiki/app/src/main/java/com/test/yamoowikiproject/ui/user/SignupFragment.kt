package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.databinding.FragmentSignupBinding
import com.test.yamoowikiproject.repository.UserRepository
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
        }

        fragmentSignupBinding.run {
            nextProfileBtn.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.PROFILE_FRAGMENT,true,false,null)
            }
        }


        return fragmentSignupBinding.root
    }


}