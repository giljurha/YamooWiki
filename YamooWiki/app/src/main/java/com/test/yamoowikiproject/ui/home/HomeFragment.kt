package com.test.yamoowikiproject.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.yamoowikiproject.databinding.FragmentHomeBinding
import com.test.yamoowikiproject.db.OpenPostEntity
import com.test.yamoowikiproject.ui.chatroom.OpenPostRoomActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* TODO: 리사이클러뷰, 레트로핏 구현예정
        val list = mutableListOf(
            OpenPostEntity(
                openPostName = "탁구",
                openPostStartDay = "나길주"
            ),
            OpenPostEntity(
                openPostName = "탁구",
                openPostStartDay = "나길주"
            )
        )

        fragmentHomeBinding.recyclerView.adapter = HomeRecyclerViewAdapter(openChatList = list) {
            val intent = Intent(context, OpenPostRoomActivity::class.java).apply {
                putExtra("OpenPostRoomActivity", 10)
            }
            startActivity(intent)
        }

        fragmentHomeBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        */
    }


}
