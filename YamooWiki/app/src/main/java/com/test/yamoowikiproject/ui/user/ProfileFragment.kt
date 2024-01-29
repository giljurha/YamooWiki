package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentProfileBinding
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.ui.home.HomeFragment
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class ProfileFragment : Fragment() {
    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var signupViewModel: SignupViewModel
    private var userModel: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        fragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
        signupViewModel = ViewModelProvider(this)[SignupViewModel::class.java]

//        val user: Class<User> = User::class.java
        userModel = arguments?.getParcelable("signInfo")
        Log.d("testt", "$userModel")

        //회원가입 결과를 받기 위한 옵저버

        signupViewModel.run{
            userModelLiveData.observe(viewLifecycleOwner){
                parentFragmentManager.popBackStack()
                replaceFragment(HomeFragment())
            }
        }

        fragmentProfileBinding.run {
            signupButton.setOnClickListener {
                if (userModel == null){
                    Toast.makeText(this@ProfileFragment.context,"회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show()
                }else{

                }
            }
        }
        return fragmentProfileBinding.root
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContainer, fragment)
        fragmentTransaction.commit()
    }

}