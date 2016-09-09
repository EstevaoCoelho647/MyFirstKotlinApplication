package com.example.estevao.myfirstkotlinapplication.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.annotations.SerializedName

/**
 * Created by estevao on 09/09/16.
 */
data class Repository(@SerializedName("id") var idWeb: Long,
                      @SerializedName("") var id: Long?,
                      var name: String,
                      @SerializedName("stargazers_count") var stargazers: Int?,
                      @JsonIgnore var idUser: Long?)