package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        mainActivity.activityMainbinding.bottomNavigationView.visibility = View.GONE


        fragmentLoginBinding.run {
            signInText.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.SINGUP_FRAGMENT, true, false, null)
            }
            loginButton.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, false, null)
            }
        }
        return fragmentLoginBinding.root
    }
}