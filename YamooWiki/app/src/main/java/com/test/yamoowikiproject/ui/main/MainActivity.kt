package com.test.yamoowikiproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.ActivityMainBinding
import com.test.yamoowikiproject.ui.main.home.HomeFragment
import com.test.yamoowikiproject.ui.main.myinfo.MyInfoFragment
import com.test.yamoowikiproject.ui.main.search.SearchFragment
import com.test.yamoowikiproject.ui.authentication.login.LoginFragment
import com.test.yamoowikiproject.ui.authentication.signup.SignupFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(fragmentType = FragmentType.HOME, addToBackStack = false, bundle = null)
//
//        mainViewModel.isVisibleBottomNavigationView.observe(this) {
//            if (it == false) {
//                binding.bottomNavigationView.visibility = View.GONE
//            } else {
//                binding.bottomNavigationView.visibility = View.VISIBLE
//            }
//        }
//
//        mainViewModel.fragmentDestination.observe(this) {
//            replaceFragment(fragmentType = it, addToBackStack = true, bundle = null)
//        }


        binding.run {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        replaceFragment(fragmentType = FragmentType.HOME, addToBackStack = false, bundle = null)
                        it.isChecked = true
                    }
                    R.id.search -> {
                        replaceFragment(fragmentType = FragmentType.SEARCH, addToBackStack = false, bundle = null)
                        it.isChecked = true
                    }
                    R.id.myInfo -> {
                        replaceFragment(fragmentType = FragmentType.MYINFO, addToBackStack = false, bundle = null)
                        it.isChecked = true
                    }
                }
                false
            }
        }
    }

    fun replaceFragment(fragmentType: FragmentType, addToBackStack: Boolean, bundle: Bundle?) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val newFragment = when (fragmentType) {
            FragmentType.LOGIN -> LoginFragment()
            FragmentType.SIGNUP -> SignupFragment()
            FragmentType.HOME -> HomeFragment()
            FragmentType.SEARCH -> SearchFragment()
            FragmentType.MYINFO -> MyInfoFragment()
        }

        newFragment.arguments = bundle
        fragmentTransaction.replace(R.id.main_container_layout, newFragment)
        if (addToBackStack) fragmentTransaction.addToBackStack(fragmentType.name)
        fragmentTransaction.commit()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }



}
