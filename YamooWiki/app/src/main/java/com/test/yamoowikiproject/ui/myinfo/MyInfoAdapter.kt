package com.test.yamoowikiproject.ui.myinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.yamoowikiproject.databinding.ItemMyInfoBinding


class MyInfoViewHolder(val itemMyInfoBinding: ItemMyInfoBinding)
    : RecyclerView.ViewHolder(itemMyInfoBinding.root)

class MyInfoAdapter(val data: MutableList<String>)
    : RecyclerView.Adapter<MyInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyInfoViewHolder {
        val itemMyInfoBinding: ItemMyInfoBinding = ItemMyInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyInfoViewHolder(itemMyInfoBinding = itemMyInfoBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyInfoViewHolder, position: Int) {
        holder.itemMyInfoBinding.textView.text = data[position]
    }

}