package com.example.maca.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.maca.R

class PostsAdapter(val posts: ArrayList<String>) : RecyclerView.Adapter<PostsAdapter.GridViewHolder>() {

    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.firstName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_post, parent, false)
        return GridViewHolder(view)
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.firstName.text = posts[position]
    }

}