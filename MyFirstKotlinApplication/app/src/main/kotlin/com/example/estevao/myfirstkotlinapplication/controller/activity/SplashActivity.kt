package com.example.estevao.myfirstkotlinapplication.controller.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.estevao.myfirstkotlinapplication.R
import com.example.estevao.myfirstkotlinapplication.model.entity.Repository
import com.example.estevao.myfirstkotlinapplication.model.entity.User
import com.example.estevao.myfirstkotlinapplication.model.http.RequisitionService
import com.example.estevao.myfirstkotlinapplication.model.persistence.RepositoryContract
import com.example.estevao.myfirstkotlinapplication.model.persistence.RepositoryRepository
import com.example.estevao.myfirstkotlinapplication.model.persistence.UserRepository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by estevao on 05/09/16.
 */

class SplashActivity : AppCompatActivity() {

    lateinit var reqService: RequisitionService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()

        buttonEnter.setOnClickListener { view ->
            startListUserActivity()
        }

        buttonUpdate.setOnClickListener { view ->
            initUserRequest()
        }
    }

    private fun initUserRequest() {
        setItemsVisibility(View.VISIBLE)
        reqService = RequisitionService()
        reqService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>) {
                val users = response.body().map { it.copy(id = null) }
                Log.i("Body: ", users.toString())
                UserRepository.deleteAll()
                UserRepository.save(users)
                initRepositoryRequest(users)

            }

            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                Log.e("Error: ", t!!.message)
            }
        })
    }


    private fun initRepositoryRequest(users: List<User>) {
        RepositoryRepository.deleteAll()
        users.forEach {
            val idWeb = it.idWeb
            reqService.getRepository(it.login).enqueue(object : Callback<List<Repository>> {
                override fun onFailure(call: Call<List<Repository>>?, t: Throwable?) {
                    Log.e("Error: ", t!!.message)
                }

                override fun onResponse(call: Call<List<Repository>>?, response: Response<List<Repository>>) {
                    val map = response.body().map { it.copy(id = null, idUser = idWeb) }
                    RepositoryRepository.save(map)
                }
            })
        }
        startListUserActivity()
    }

    private fun setItemsVisibility(visible: Int) {
        if (visible == View.VISIBLE) {
            buttonEnter.visibility = View.GONE
            buttonUpdate.visibility = View.GONE
            progress.visibility = View.VISIBLE
            textViewMessage.visibility = View.VISIBLE
        } else {
            buttonEnter.visibility = View.VISIBLE
            buttonUpdate.visibility = View.VISIBLE
            progress.visibility = View.GONE
            textViewMessage.visibility = View.GONE
        }
    }

    private fun startListUserActivity() {
        val intent: Intent = Intent(this@SplashActivity, UserListActivity::class.java)
        startActivity(intent)
    }
}

