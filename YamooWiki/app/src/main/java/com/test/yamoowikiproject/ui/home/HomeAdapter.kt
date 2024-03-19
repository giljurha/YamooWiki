package com.test.yamoowikiproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.yamoowikiproject.databinding.ItemHomeBinding
import com.test.yamoowikiproject.db.OpenChatEntity

class MyViewHolder(
    binding: ItemHomeBinding,
    private val onClickItem: (OpenChatEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val openChatId = binding.openChatId
    private val openChatTitle = binding.openChatTitle
    private val root = binding.root

    fun onBind(openChatData: OpenChatEntity) {
        openChatId.text = openChatData.id.toString()
        openChatTitle.text = openChatData.openChatName
        root.setOnClickListener {
            onClickItem(openChatData)
        }
    }
}

class HomeRecyclerViewAdapter(
    private val openChatList: ArrayList<OpenChatEntity>,
    private val onClickItem: (OpenChatEntity) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemHomeBinding = ItemHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding, onClickItem)
    }
    override fun getItemCount(): Int = openChatList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val openChatData = openChatList[position]
        holder.onBind(openChatData)
    }

}
