package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.ActivityAuthenticationBinding
import com.test.yamoowikiproject.ui.home.HomeFragment
import com.test.yamoowikiproject.viewmodel.LoginViewModel


class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(LoginFragmentType.HOME)

        loginViewModel.loginFragment.observe(this) {
            replaceFragment(it)
        }
    }



    fun replaceFragment(loginFragmentType: LoginFragmentType) {
        val replaceFragment: Fragment = when (loginFragmentType) {
            LoginFragmentType.SIGNUP -> SignupFragment()
            LoginFragmentType.HOME -> HomeFragment()
        }
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.login_container_layout, replaceFragment)
        fragmentTransaction.addToBackStack("fragment")
        fragmentTransaction.commit()
    }
}