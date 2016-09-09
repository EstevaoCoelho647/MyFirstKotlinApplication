package com.example.estevao.myfirstkotlinapplication.controller.adapter

import android.content.Context
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.estevao.myfirstkotlinapplication.R
import com.example.estevao.myfirstkotlinapplication.model.entity.User

/**
 * Created by estevao on 08/09/16.
 */
class UserListAdapter(var context: Context, val users: List<User>) : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        val li = LayoutInflater.from(context)
        val v = li.inflate(R.layout.item_user, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)

        holder.txtViewUserName.text = user.login
        holder.txtViewUserRepository.text = user.repositoryUrl

        Glide.with(context).load(user.avatar).error(R.drawable.no_image).placeholder(R.drawable.no_image).centerCrop().into(holder.imgViewUserImage)

    }

    fun getItem(position: Int): User {
        return users[position]
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var txtViewUserName: TextView
        internal var txtViewUserRepository: TextView
        internal var imgViewUserImage: ImageView

        init {
            val linearLayout = itemView.findViewById(R.id.linearLayout) as LinearLayout
            txtViewUserName = linearLayout.findViewById(R.id.userName) as TextView
            txtViewUserRepository = linearLayout.findViewById(R.id.userRepository) as TextView
            imgViewUserImage = itemView.findViewById(R.id.userAvatar) as ImageView
        }
    }
}


