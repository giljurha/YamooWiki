package com.test.yamoowikiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
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

//        if( preference 체크값 false ) replaceFragment 호출 후 btmNaviView visibility View.Gone
        replaceFragment(LOGIN_FRAGMENT, false, false, null)
//        activityMainbinding.bottomNavigationView.visibility = View.GONE

        activityMainbinding.run {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> replaceFragment(HOME_FRAGMENT, false, false, null)
                    R.id.search -> replaceFragment(SEARCH_FRAGMENT, false, false, null)
                    R.id.myInfo -> replaceFragment(MYINFO_FRAGMENT, false, false, null)
                }
                false
            }
        }
    }

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

        if (newFragment != null) {

            fragmentTransaction.replace(R.id.mainContainer, newFragment)

            if (addToBackStack == true) fragmentTransaction.addToBackStack(name)

            fragmentTransaction.commit()
        }
    }

}
