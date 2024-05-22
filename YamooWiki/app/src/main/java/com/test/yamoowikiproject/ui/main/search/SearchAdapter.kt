package com.test.yamoowikiproject.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.yamoowikiproject.databinding.ItemSearchBinding


class SearchViewHolder(val itemSearchBinding: ItemSearchBinding)
    : RecyclerView.ViewHolder(itemSearchBinding.root)

class SearchViewAdapter(val data: MutableList<Int>)
    : RecyclerView.Adapter<SearchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemSearchBinding: ItemSearchBinding = ItemSearchBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SearchViewHolder(itemSearchBinding = itemSearchBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.itemSearchBinding.textView.text = data[position].toString()
    }

}
