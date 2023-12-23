package com.test.yamoowikiproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.yamoowikiproject.ui.user.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = LoginFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        transaction.add(R.id.mainLayout, fragment)
        transaction.commit()
    }
}