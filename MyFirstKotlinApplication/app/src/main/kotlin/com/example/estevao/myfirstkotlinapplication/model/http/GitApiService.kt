package com.example.estevao.myfirstkotlinapplication.model.http

import com.example.estevao.myfirstkotlinapplication.model.entity.Repository
import com.example.estevao.myfirstkotlinapplication.model.entity.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by estevao on 06/09/16.
 */
interface GitApiService {

    //getUsers
    @GET("users?client_id=14892c7d9e130dc3e87c&client_secret=42932fc3eb882c3c540854f2d694561545282394")
    fun listUsers(): Call<List<User>>

    @GET("users/{user}/repos?client_id=14892c7d9e130dc3e87c&client_secret=42932fc3eb882c3c540854f2d694561545282394")
    fun listRepositories(@Path("user") userName: String): Call<List<Repository>>
}
