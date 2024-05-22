package com.test.yamoowikiproject.ui.main.myinfo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.test.yamoowikiproject.databinding.FragmentMyinfoBinding
import com.test.yamoowikiproject.db.UserEntity
import com.test.yamoowikiproject.retrofit.AddressService
import com.test.yamoowikiproject.retrofit.RetrofitConnection
import com.test.yamoowikiproject.ui.authentication.login.LoginViewModel
import kotlinx.coroutines.launch


class MyInfoFragment : Fragment() {
    private lateinit var binding: FragmentMyinfoBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private var userId: String? = null
    private var userImageUri: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyinfoBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.loginUserEntity.observe(viewLifecycleOwner){
            readSharedPreferences(it, requireContext())
        }
        setUserImage()

/* TODO: 내정보 구현중
        val data = mutableListOf<String>("축구","농구")
        signupViewModel.signup(userEntity = confirm(), context = requireContext())
        fragmentMyInfoBinding.recyclerView.adapter = MyInfoAdapter(data)
        fragmentMyInfoBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val isLogin = loginViewModel.isLogin
        val isLogin2 = signupViewModel.k?.userImageUri
        mainViewModel.getCurrentUser(requireContext())


 */

        val addressService = RetrofitConnection.retrofit.create(AddressService::class.java)
        viewLifecycleOwner.lifecycleScope.launch {
            addressService.getTopAddressList().list.forEach {
                Log.d("레트로핏","${it.value}")
            }
        }
    }

    private fun setUserImage() {
        Glide.with(this).load(userImageUri).into(binding.imageView)
    }


    fun readSharedPreferences(userEntity: UserEntity, context: Context) {
        val sharedPreferences = context
            .getSharedPreferences("loginUser", Context.MODE_PRIVATE)
        this.userId = sharedPreferences.getString("userId", "")
        this.userImageUri = sharedPreferences.getString("userImageUri","")
    }
}
