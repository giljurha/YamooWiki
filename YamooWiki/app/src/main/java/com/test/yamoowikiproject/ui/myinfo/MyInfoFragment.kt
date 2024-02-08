package com.test.yamoowikiproject.ui.myinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentMyinfoBinding


class MyInfoFragment : Fragment() {
    lateinit var fragmentMyInfoBinding: FragmentMyinfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentMyInfoBinding = FragmentMyinfoBinding.inflate(layoutInflater)
        return fragmentMyInfoBinding.root
    }
}