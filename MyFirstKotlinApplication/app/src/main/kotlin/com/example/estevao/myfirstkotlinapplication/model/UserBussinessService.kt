package com.example.estevao.myfirstkotlinapplication.model

import com.example.estevao.myfirstkotlinapplication.model.entity.User
import com.example.estevao.myfirstkotlinapplication.model.persistence.UserRepository

/**
 * Created by estevao on 08/09/16.
 */

fun save(users: List<User>) {
    UserRepository.save(users)
}

fun findAllUsers(): List<User> {
    return UserRepository.all
}
