package com.test.yamoowikiproject.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.KeyEvent
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.ActivityMainBinding
import com.test.yamoowikiproject.ui.home.HomeFragment
import com.test.yamoowikiproject.ui.myinfo.MyInfoFragment
import com.test.yamoowikiproject.ui.search.SearchFragment
import com.test.yamoowikiproject.ui.user.LoginFragment
import com.test.yamoowikiproject.ui.user.ProfileFragment
import com.test.yamoowikiproject.ui.user.SignupFragment


class MainActivity : AppCompatActivity() {

    lateinit var activityMainbinding: ActivityMainBinding

    companion object {
        val LOGIN_FRAGMENT = "LoginFragment"
        val SINGUP_FRAGMENT = "SignUpFragment"
        val PROFILE_FRAGMENT = "ProfileFragment"
        val HOME_FRAGMENT = "HomeFragment"
        val SEARCH_FRAGMENT = "SearchFragment"
        val MYINFO_FRAGMENT = "MyInfoFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainbinding.root)

//        if( preference 체크값 false ) replaceFragment 호출
        replaceFragment(LOGIN_FRAGMENT, false, false, null)

        activityMainbinding.run {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        replaceFragment(HOME_FRAGMENT, false, false, null)
                        it.isChecked = true
                    }
                    R.id.search -> {
                        replaceFragment(SEARCH_FRAGMENT, false, false, null)
                        it.isChecked = true
                    }
                    R.id.myInfo -> {
                        replaceFragment(MYINFO_FRAGMENT, false, false, null)
                        it.isChecked = true
                    }
                }
                false
            }
        }
    }

//    if 프리퍼런스 값 트루 그렇지 않을 때는 사용하지 않음
//    private var backPressedOnce = false
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (backPressedOnce) {
//                finish() // 앱 종료
//            } else {
//                backPressedOnce = true
//                Toast.makeText(this, "한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
//
//                // 2초 안에 다시 뒤로가기 버튼을 누르지 않으면 상태 초기화
//                Handler().postDelayed({ backPressedOnce = false }, 2000)
//            }
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }

    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean, bundle: Bundle?) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var newFragment = when (name) {
            LOGIN_FRAGMENT -> LoginFragment()
            SINGUP_FRAGMENT -> SignupFragment()
            PROFILE_FRAGMENT -> ProfileFragment()
            HOME_FRAGMENT -> HomeFragment()
            SEARCH_FRAGMENT -> SearchFragment()
            MYINFO_FRAGMENT -> MyInfoFragment()
            else -> Fragment()
        }

        newFragment.arguments = bundle

        fragmentTransaction.replace(R.id.mainContainer, newFragment)

        if (addToBackStack == true) fragmentTransaction.addToBackStack(name)

        fragmentTransaction.commit()

    }

    fun popFragment(name: String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }


}


