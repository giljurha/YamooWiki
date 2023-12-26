package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.yamoowikiproject.MainActivity
import com.test.yamoowikiproject.R
import kotlin.concurrent.fixedRateTimer


class LoginFragment : Fragment() {
    lateinit var mainActivity:MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_login, container, false)


    }


}