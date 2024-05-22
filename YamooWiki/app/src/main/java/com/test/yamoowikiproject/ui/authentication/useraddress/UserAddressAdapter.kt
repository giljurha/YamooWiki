package com.test.yamoowikiproject.ui.authentication.useraddress

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.yamoowikiproject.databinding.ItemAddressBinding
import com.test.yamoowikiproject.retrofit.Address


class UserAddressViewHolder(
    private val itemAddressBinding: ItemAddressBinding
) : RecyclerView.ViewHolder(itemAddressBinding.root) {
    fun onBind(userData: Address) {
        val userAddress =
            "${userData.region1depthName} ${userData.region2depthName} ${userData.region3depthName}"
        itemAddressBinding.textView.text = userAddress
    }
}

class UserAddressAdapter(val data: MutableList<Address>) :
    RecyclerView.Adapter<UserAddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAddressViewHolder {
        val itemAddressBinding: ItemAddressBinding = ItemAddressBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserAddressViewHolder(itemAddressBinding = itemAddressBinding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserAddressViewHolder, position: Int) {
        val userData = data[position]
        holder.onBind(userData)
    }

}