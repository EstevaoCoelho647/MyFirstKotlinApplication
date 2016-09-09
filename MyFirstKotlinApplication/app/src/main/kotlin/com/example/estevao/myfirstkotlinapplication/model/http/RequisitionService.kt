package com.example.estevao.myfirstkotlinapplication.model.http

import com.example.estevao.myfirstkotlinapplication.model.entity.Repository
import com.example.estevao.myfirstkotlinapplication.model.entity.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by estevao on 06/09/16.
 */
class RequisitionService {
    private val gitApi: GitApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        gitApi = retrofit.create(GitApiService::class.java)
    }

    fun getUsers(): Call<List<User>> {
        return gitApi.listUsers()
    }

    fun getRepository(userName: String): Call<List<Repository>> {
        return gitApi.listRepositories(userName)
    }

}
