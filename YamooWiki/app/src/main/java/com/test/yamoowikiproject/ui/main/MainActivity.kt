package com.test.yamoowikiproject.ui.main

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.ActivityMainBinding
import com.test.yamoowikiproject.ui.chatroom.OpenChatRoomFragment
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
        replaceFragment(FragmentType.LOGIN, false, false, null)

        mainViewModel.isVisibleBottomNavigationView.observe(this) {
            if (it == false) {
                activityMainbinding.bottomNavigationView.visibility = View.GONE
            } else {
                activityMainbinding.bottomNavigationView.visibility = View.VISIBLE
            }
        }

        mainViewModel.fragmentDestination.observe(this) {
            replaceFragment(it, false, false, null)
        }


        activityMainbinding.run {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        replaceFragment(FragmentType.HOME, false, false, null)
                        it.isChecked = true
                    }
                    R.id.search -> {
                        replaceFragment(FragmentType.SEARCH, false, false, null)
                        it.isChecked = true
                    }
                    R.id.myInfo -> {
                        replaceFragment(FragmentType.MYINFO, false, false, null)
                        it.isChecked = true
                    }
                }
                false
            }
        }
    }

    private var backPressedOnce = false

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (backPressedOnce) {
                finish() // 앱 종료
            } else {
                backPressedOnce = true
                Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({ backPressedOnce = false }, 2000)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    fun replaceFragment(type: FragmentType, addToBackStack: Boolean, animate: Boolean, bundle: Bundle?) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val newFragment = when (type) {
            FragmentType.LOGIN -> LoginFragment()
            FragmentType.SIGNUP -> SignupFragment()
            FragmentType.HOME -> HomeFragment()
            FragmentType.SEARCH -> SearchFragment()
            FragmentType.MYINFO -> MyInfoFragment()
            FragmentType.OPENCHATROOM -> OpenChatRoomFragment()
        }

        newFragment.arguments = bundle
        fragmentTransaction.replace(R.id.mainContainer, newFragment)
        if (addToBackStack) fragmentTransaction.addToBackStack(type.name)
        fragmentTransaction.commit()
    }

    fun popFragment(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
