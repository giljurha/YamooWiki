package com.test.yamoowikiproject.ui.authentication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.ActivityAuthenticationBinding
import com.test.yamoowikiproject.ui.authentication.login.LoginFragment
import com.test.yamoowikiproject.ui.authentication.model.AuthenticationFragmentType
import com.test.yamoowikiproject.ui.authentication.signup.SignupFragment
import com.test.yamoowikiproject.ui.authentication.useraddress.UserAddressFragment


class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(AuthenticationFragmentType.LOGIN)

        authenticationViewModel.authenticationFragmentType.observe(this) {
            replaceFragment(it)
        }
    }

    private fun replaceFragment(authenticationFragmentType: AuthenticationFragmentType) {
        val fragment: Fragment = when (authenticationFragmentType) {
            AuthenticationFragmentType.LOGIN -> LoginFragment()
            AuthenticationFragmentType.SIGNUP -> SignupFragment()
            AuthenticationFragmentType.ADDRESS -> UserAddressFragment()
        }
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.authentication_container_layout, fragment)
        fragmentTransaction.addToBackStack("fragment")
        fragmentTransaction.commit()
    }
}