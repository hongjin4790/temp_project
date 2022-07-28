package com.example.gdsc_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_project.R

import kotlin.collections.ArrayList

class FieldAdapter(private val dataset:ArrayList<policy>): RecyclerView.Adapter<FieldAdapter.NewsViewHolder>() {
    class NewsViewHolder(private val view : View) : RecyclerView.ViewHolder(view){
        val textTitle: TextView = view.findViewById(R.id.item_title)
        val textCount: TextView = view.findViewById(R.id.item_count)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return NewsViewHolder(layout)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val item = dataset[position]
        holder.textTitle.text = item.정책명.toString()
        holder.textCount.text = item.지원분야.toString()

    }


    override fun getItemCount(): Int {
        return dataset.size
    }
}