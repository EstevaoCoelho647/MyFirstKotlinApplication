package com.example.estevao.myfirstkotlinapplication.controller.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.estevao.myfirstkotlinapplication.R
import com.example.estevao.myfirstkotlinapplication.controller.adapter.RepositoryAdapter
import com.example.estevao.myfirstkotlinapplication.model.entity.User
import com.example.estevao.myfirstkotlinapplication.model.persistence.RepositoryRepository
import kotlinx.android.synthetic.main.activity_user_view.*

/**
 * Created by estevao on 09/09/16.
 */
class UserViewActivity : AppCompatActivity() {

    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_view)
        title = "Repositories"

        user = bindExtras()

        Glide.with(this).load(user.avatar).error(R.drawable.no_image).placeholder(R.drawable.no_image).centerCrop().into(userAvatar)
        userName.text = user.login
        userRepository.text = user.repositoryUrl

        recyclerRepository.layoutManager = LinearLayoutManager(this)
        val repositoryAdapter = RepositoryAdapter(this, RepositoryRepository.findByIdWeb(user.idWeb))
        recyclerRepository.adapter = repositoryAdapter
        repositoryAdapter.notifyDataSetChanged()

    }

    private fun bindExtras(): User {
        return intent.extras.getParcelable(UserListActivity.USER_ID)
    }
}