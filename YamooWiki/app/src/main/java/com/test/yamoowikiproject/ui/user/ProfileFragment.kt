package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.yamoowikiproject.MainActivity
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentProfileBinding
import com.test.yamoowikiproject.databinding.FragmentSignupBinding


class ProfileFragment : Fragment() {
    lateinit var fragmentProfileBinding: FragmentProfileBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        mainActivity = activity as MainActivity
        fragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment

        fragmentProfileBinding.run {
            signUpbutton.setOnClickListener {
                mainActivity.removeFragment(MainActivity.SINGUP_FRAGMENT)
                mainActivity.removeFragment(MainActivity.LOGIN_FRAGMENT)
                mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, false, null)
            }
        }

        return fragmentProfileBinding.root
    }


}