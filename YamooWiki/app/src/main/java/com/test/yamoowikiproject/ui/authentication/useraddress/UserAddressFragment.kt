package com.test.yamoowikiproject.ui.authentication.useraddress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.yamoowikiproject.databinding.FragmentUserAddressBinding
import com.test.yamoowikiproject.retrofit.Address


class UserAddressFragment : Fragment() {
    lateinit var binding: FragmentUserAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserAddressBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvAddress.adapter = UserAddressAdapter(mutableListOf())
        binding.rvAddress.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initViews() {
        with(binding) {
            btnAddAddress.setOnClickListener {
                val etAddressName: String = etAddressName.text.toString()
            }
        }
    }
}