package com.test.yamoowikiproject.ui.myinfo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.yamoowikiproject.databinding.FragmentMyinfoBinding
import com.test.yamoowikiproject.viewmodel.LoginViewModel
import com.test.yamoowikiproject.viewmodel.MainViewModel
import com.test.yamoowikiproject.viewmodel.SignupViewModel
import kotlin.math.sign


class MyInfoFragment : Fragment() {
    lateinit var fragmentMyInfoBinding: FragmentMyinfoBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentMyInfoBinding = FragmentMyinfoBinding.inflate(layoutInflater)
        return fragmentMyInfoBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
/* TODO: 내정보 구현중
        val data = mutableListOf<String>("축구","농구")
        signupViewModel.signup(userEntity = confirm(), context = requireContext())
        fragmentMyInfoBinding.recyclerView.adapter = MyInfoAdapter(data)
        fragmentMyInfoBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val isLogin = loginViewModel.isLogin
        val isLogin2 = signupViewModel.k?.userImageUri
        mainViewModel.getCurrentUser(requireContext())

    fun getCurrentUser(context: Context) {
        val k = context
            .getSharedPreferences("currentUser", Context.MODE_PRIVATE)
            .getString("userId", "데이터 없음") ?: "데이터 없음"}

 */


    }
}
