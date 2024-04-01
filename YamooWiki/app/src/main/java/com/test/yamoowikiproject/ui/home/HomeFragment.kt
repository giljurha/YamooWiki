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
import com.test.yamoowikiproject.ui.chatroom.OpenChatRoomActivity
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

        // db에 저장해서 db에 있는 내용을 불러오는 형식으로 바꾸기
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

        fragmentHomeBinding.recyclerView.adapter = HomeRecyclerViewAdapter(list) {
            val intent = Intent(context, OpenChatRoomActivity::class.java).apply {
                putExtra("OpenChatRoomActivity", "")
            }
            startActivity(intent)
        }

        fragmentHomeBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }


}
