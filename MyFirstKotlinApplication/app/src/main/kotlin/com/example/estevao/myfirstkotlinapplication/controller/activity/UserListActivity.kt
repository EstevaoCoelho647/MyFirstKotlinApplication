package com.example.estevao.myfirstkotlinapplication.controller.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.estevao.myfirstkotlinapplication.R
import com.example.estevao.myfirstkotlinapplication.controller.adapter.OnItemClickListener
import com.example.estevao.myfirstkotlinapplication.controller.adapter.UserListAdapter
import com.example.estevao.myfirstkotlinapplication.controller.adapter.setOnItemClickListener
import com.example.estevao.myfirstkotlinapplication.model.entity.User
import com.example.estevao.myfirstkotlinapplication.model.persistence.UserRepository
import kotlinx.android.synthetic.main.activity_user_list.*

/**
 * Created by estevao on 08/09/16.
 */
class UserListActivity : AppCompatActivity() {

    companion object {
        val USER_ID: String = "USERID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        title = "User list"


        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = llm
        val adapter: UserListAdapter = UserListAdapter(this@UserListActivity, UserRepository.all)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()

        recycler.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(recyclerView: RecyclerView, v: View, position: Int, id: Long) {
                val intent: Intent = Intent(this@UserListActivity, UserViewActivity::class.java)
                intent.putExtra(USER_ID, adapter.getItem(position))
                startActivity(intent)
            }
        })
    }
}