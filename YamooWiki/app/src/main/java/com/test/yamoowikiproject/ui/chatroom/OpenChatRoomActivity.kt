package com.test.yamoowikiproject.ui.chatroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.test.yamoowikiproject.R
import com.test.yamoowikiproject.databinding.ActivityOpenChatRoomBinding


class OpenChatRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOpenChatRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* TODO:  */
        /* TODO: 사진 로컬 갤러리에서 접근해서 올리기 */
    }

}