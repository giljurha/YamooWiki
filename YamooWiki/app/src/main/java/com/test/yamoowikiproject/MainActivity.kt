package com.test.yamoowikiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.yamoowikiproject.databinding.ActivityMainBinding
import com.test.yamoowikiproject.ui.user.LoginFragment
import com.test.yamoowikiproject.ui.user.SignupFragment

class MainActivity : AppCompatActivity() {
    lateinit var activityMainbinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityMainbinding = ActivityMainBinding.inflate(layoutInflater)

        val fragment = SignupFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.add(R.id.mainContainer, fragment)
        transaction.commit()
    }
}