package com.test.yamoowikiproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.yamoowikiproject.databinding.FragmentHomeBinding
import com.test.yamoowikiproject.db.OpenChatEntity
import com.test.yamoowikiproject.ui.main.FragmentType
import com.test.yamoowikiproject.viewmodel.MainViewModel


class HomeFragment : Fragment() {
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return fragmentHomeBinding.root
    }
    /*TODO: 뒤로가기 눌렀을 경우 앱 종료 될 수 있도록 설정해야함.*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.isVisibleBottomNavigationView.value = true

        fragmentHomeBinding.recyclerView.adapter = HomeRecyclerViewAdapter(arrayListOf(
            OpenChatEntity(
                openChatName = "탁구",
                openChatOpener = "나길주",
                openChatStartDay = "0215"
            ),
            OpenChatEntity(
                openChatName = "축구",
                openChatOpener = "손흥민",
                openChatStartDay = "0217"
            )
        ))
        fragmentHomeBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fragmentHomeBinding.recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                TODO("Not yet implemented")
                mainViewModel.fragmentDestination.value = FragmentType.OPENCHATROOM
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("Not yet implemented")
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("Not yet implemented")
            }

        })
    }


}

