package com.sdv.kit.application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sdv.kit.application.R
import com.sdv.kit.application.callback.ItemCallback1
import com.sdv.kit.application.entity.User
import com.sdv.kit.application.viewholder.ViewHolder1

class ListAdapter1(context: Context) : ListAdapter<User, ViewHolder1>(ItemCallback1()) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 =
        ViewHolder1(inflater.inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) =
        holder.bind(getItem(position))
}