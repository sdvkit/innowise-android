package com.sdv.kit.application.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sdv.kit.application.R
import com.sdv.kit.application.callback.ItemCallback2
import com.sdv.kit.application.entity.User
import com.sdv.kit.application.viewholder.ViewHolder2

class ListAdapter2(context: Context) : ListAdapter<User, ViewHolder2>(ItemCallback2()) {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 =
        ViewHolder2(inflater.inflate(R.layout.card_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) =
        holder.bind(getItem(position))
}