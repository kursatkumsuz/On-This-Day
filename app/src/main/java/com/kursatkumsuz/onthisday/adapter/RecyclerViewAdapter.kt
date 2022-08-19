package com.kursatkumsuz.onthisday.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kursatkumsuz.onthisday.databinding.ListItemBinding
import com.kursatkumsuz.onthisday.model.PostModel

class RecyclerViewAdapter (val list : ArrayList<PostModel>) : RecyclerView.Adapter<RecyclerViewAdapter.PostHolder>() {
    class PostHolder(val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.subText.text = list[position].post
    }

    override fun getItemCount(): Int {
        return list.size
    }
}