package com.test.yamoowikiproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.database.database

import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var mainActivity: MainActivity
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        mainActivity.activityMainbinding.bottomNavigationView.visibility = View.VISIBLE
        val database = Firebase.database
        val test1 = database.getReference("test")
        test1.setValue("Hello World")

        return fragmentHomeBinding.root
    }

    //TODO: 뒤로가기 눌렀을 경우 앱 종료 될 수 있도록 설정해야함.
}