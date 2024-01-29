package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentLoginBinding
import com.test.yamoowikiproject.ui.home.HomeFragment
import com.test.yamoowikiproject.viewmodel.MainViewModel

class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        return fragmentLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val k = (activity as MainActivity).activityMainbinding

        mainViewModel.isVisibleBottomNavigationView.value = false

        fragmentLoginBinding.run {
            signInText.setOnClickListener {

                replaceFragment(SignupFragment())

            }
            loginButton.setOnClickListener {

                replaceFragment(HomeFragment())
            }
        }
    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContainer, fragment)
        fragmentTransaction.commit()
    }

}