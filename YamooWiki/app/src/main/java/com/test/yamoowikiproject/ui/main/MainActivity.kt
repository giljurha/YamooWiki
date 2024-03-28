package com.test.yamoowikiproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.ActivityMainBinding
import com.test.yamoowikiproject.ui.chatroom.OpenChatRoomActivity
import com.test.yamoowikiproject.ui.home.HomeFragment
import com.test.yamoowikiproject.ui.myinfo.MyInfoFragment
import com.test.yamoowikiproject.ui.search.SearchFragment
import com.test.yamoowikiproject.ui.user.LoginFragment
import com.test.yamoowikiproject.ui.user.SignupFragment
import com.test.yamoowikiproject.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    lateinit var activityMainbinding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainbinding.root)

        /* TODO: preference 값 체크 후 로그인 수행 */
        replaceFragment(FragmentType.LOGIN, false, null)

        mainViewModel.isVisibleBottomNavigationView.observe(this) {
            if (it == false) {
                activityMainbinding.bottomNavigationView.visibility = View.GONE
            } else {
                activityMainbinding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

        mainViewModel.fragmentDestination.observe(this) {
            replaceFragment(it, false, null)
        }


        activityMainbinding.run {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        replaceFragment(FragmentType.HOME, false, null)
                        it.isChecked = true
                    }
                    R.id.search -> {
                        replaceFragment(FragmentType.SEARCH, false,null)
                        it.isChecked = true
                    }
                    R.id.myInfo -> {
                        replaceFragment(FragmentType.MYINFO, false,null)
                        it.isChecked = true
                    }
                }
                false
            }
        }
    }

    fun replaceFragment(type: FragmentType, addToBackStack: Boolean, bundle: Bundle?) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val newFragment = when (type) {
            FragmentType.LOGIN -> LoginFragment()
            FragmentType.SIGNUP -> SignupFragment()
            FragmentType.HOME -> HomeFragment()
            FragmentType.SEARCH -> SearchFragment()
            FragmentType.MYINFO -> MyInfoFragment()
        }

        newFragment.arguments = bundle
        fragmentTransaction.replace(R.id.mainContainer, newFragment)
        if (addToBackStack) fragmentTransaction.addToBackStack(type.name)
        fragmentTransaction.commit()
    }

    fun popFragment(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

}
