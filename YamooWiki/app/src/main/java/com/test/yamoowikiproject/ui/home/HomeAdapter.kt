package com.test.yamoowikiproject.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.yamoowikiproject.databinding.ItemHomeBinding
import com.test.yamoowikiproject.db.OpenPostEntity

class MyViewHolder(
    private val binding: ItemHomeBinding,
    private val onClickItem: (OpenPostEntity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(openChatData: OpenPostEntity) {

        with(binding) {
            openChatId.text = openChatData.id.toString()
            openChatTitle.text = openChatData.openPostName
            root.setOnClickListener {
                onClickItem(openChatData)
            }
        }
    }
}

class HomeRecyclerViewAdapter(
    private val openChatList: MutableList<OpenPostEntity>,
    private val onClickItem: (OpenPostEntity) -> Unit
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

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}
