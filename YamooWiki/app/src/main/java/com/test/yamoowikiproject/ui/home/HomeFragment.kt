package com.test.yamoowikiproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels


import com.test.yamoowikiproject.ui.main.MainActivity
import com.test.yamoowikiproject.databinding.FragmentHomeBinding
import com.test.yamoowikiproject.viewmodel.MainViewModel


class HomeFragment : Fragment() {
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return fragmentHomeBinding.root
    }
    /*TODO: 뒤로가기 눌렀을 경우 앱 종료 될 수 있도록 설정해야함.*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.isVisibleBottomNavigationView.value = true
    }
}
