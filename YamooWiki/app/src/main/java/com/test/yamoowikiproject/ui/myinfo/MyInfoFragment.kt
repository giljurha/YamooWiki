package com.test.yamoowikiproject.ui.myinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.yamoowikiproject.databinding.FragmentMyinfoBinding
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class MyInfoFragment : Fragment() {
    lateinit var fragmentMyInfoBinding: FragmentMyinfoBinding
    private val signupViewModel: SignupViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentMyInfoBinding = FragmentMyinfoBinding.inflate(layoutInflater)
        return fragmentMyInfoBinding.root
    }

    val data = mutableListOf<String>("축구","농구")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        signupViewModel.signup(userEntity = confirm(), context = requireContext())
        fragmentMyInfoBinding.recyclerView.adapter = MyInfoAdapter(data)
        fragmentMyInfoBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}