package com.test.yamoowikiproject.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.yamoowikiproject.MainActivity
import com.test.yamoowikiproject.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    lateinit var fragmentSignupBinding: FragmentSignupBinding
    lateinit var mainActivity:MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        mainActivity = activity as MainActivity
        fragmentSignupBinding = FragmentSignupBinding.inflate(layoutInflater)

        fragmentSignupBinding.run {
            nextBtn.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.PROFILE_FRAGMENT,true,false,null)
            }
        }


        return fragmentSignupBinding.root
    }


}