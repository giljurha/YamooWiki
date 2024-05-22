package com.test.yamoowikiproject.ui.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* TODO: 리사이클러뷰, 레트로핏 구현예정
        val ok = mutableListOf(1,2,3,4,5,6,7,8,9)
        fragmentSearchBiding.recyclerView.adapter = SearchRecyclerViewAdapter(ok)
        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        val gridLayoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL, false)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        fragmentSearchBiding.recyclerView.layoutManager = gridLayoutManager
        */
    }
}
