package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.test.yamoowikiproject.databinding.FragmentProfileBinding
import com.test.yamoowikiproject.dataclassmodel.User
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.viewmodel.MainViewModel
import com.test.yamoowikiproject.viewmodel.SignupViewModel


class ProfileFragment : Fragment() {
    private lateinit var fragmentProfileBinding: FragmentProfileBinding
    private lateinit var signupViewModel: SignupViewModel
    private var userModel: User? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
        return fragmentProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userModel = arguments?.getParcelable("signInfo")

        fragmentProfileBinding.run{
            signupButton.setOnClickListener {
                Toast.makeText(this@ProfileFragment.context,"회원가입에 성공하였습니다.",Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
                mainViewModel.fragmentDestination.value = FragmentType.HOME
            }
        }
    }
}
