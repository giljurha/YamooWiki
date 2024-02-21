package com.test.yamoowikiproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.yamoowikiproject.databinding.ItemHomeBinding
import com.test.yamoowikiproject.db.OpenChatEntity

class MyViewHolder(binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
    private val openChatId = binding.openChatId
    private val openChatTitle = binding.openChatTitle
    private val root = binding.root

    fun onBind(openChatData: OpenChatEntity) {
        openChatId.text = openChatData.id.toString()
        openChatTitle.text = openChatData.openChatName
    }
}

class HomeRecyclerViewAdapter(private val openChatList: ArrayList<OpenChatEntity>) :
    RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemHomeBinding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return openChatList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val openChatData = openChatList[position]
        holder.onBind(openChatData)
    }
}
