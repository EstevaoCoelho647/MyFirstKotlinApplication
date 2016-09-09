package com.example.estevao.myfirstkotlinapplication.controller.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.estevao.myfirstkotlinapplication.R
import com.example.estevao.myfirstkotlinapplication.model.entity.Repository

/**
 * Created by estevao on 09/09/16.
 */

class RepositoryAdapter(val context: Context, val repositories: List<Repository>) : RecyclerView.Adapter<RepositoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val from = LayoutInflater.from(context)
        val inflate = from.inflate(R.layout.item_repository, parent, false)
        return MyViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val get = repositories[position]

        holder.txtStargazers.text = get.stargazers.toString()
        holder.txtViewRepositoryName.text = get.name
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var txtViewRepositoryName: TextView
        lateinit var txtStargazers: TextView

        init {
            txtViewRepositoryName = itemView.findViewById(R.id.repository_name) as TextView
            txtStargazers = itemView.findViewById(R.id.stargazers) as TextView
        }
    }

}