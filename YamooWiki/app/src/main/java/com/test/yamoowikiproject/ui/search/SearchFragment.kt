package com.test.yamoowikiproject.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    lateinit var fragmentSearchBiding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        fragmentSearchBiding = FragmentSearchBinding.inflate(layoutInflater)
        return fragmentSearchBiding.root
    }
}