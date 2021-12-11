package com.example.android.intivetaskone

import androidx.recyclerview.widget.RecyclerView
import com.example.android.intivetaskone.R
import android.app.Activity
import android.content.Context

import android.widget.TextView

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import com.example.android.intivetaskone.network.InfoProperty

class RecyclerViewAdapter(val userList: List<InfoProperty>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_view_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(info: InfoProperty) {
            val title = itemView.findViewById<TextView>(R.id.text_title)
            val desc = itemView.findViewById<TextView>(R.id.text_desc)

            title.text = info.title
            desc.text = info.desc
        }
    }
}
