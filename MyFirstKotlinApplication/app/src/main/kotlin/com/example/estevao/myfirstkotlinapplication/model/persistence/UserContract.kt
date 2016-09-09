package com.example.estevao.myfirstkotlinapplication.model.persistence

import android.content.ContentValues
import android.database.Cursor
import com.example.estevao.myfirstkotlinapplication.model.entity.User

/**
 * Created by estevao on 06/09/16.
 */
object UserContract {

    val TABLE: String = "usertable"
    val ID: String = "id"
    val IDWEB: String = "idweb"
    val LOGIN: String = "login"
    val AVATAR: String = "avatar"
    val REPOSITORY_URL: String = "repositoryUrl"
    val AVATAR_BYTES: String = "avatarBytes"

    val columns: Array<String> = arrayOf(ID, IDWEB, LOGIN, AVATAR, AVATAR_BYTES, REPOSITORY_URL)


    fun getCreateTableScript(): String {
        val create: StringBuilder = StringBuilder()

        create.append(" CREATE TABLE " + TABLE)
        create.append(" ( ")
        create.append(ID + " INTEGER PRIMARY KEY, ")
        create.append(IDWEB + " INTEGER, ")
        create.append(LOGIN + " TEXT, ")
        create.append(AVATAR + " TEXT, ")
        create.append(AVATAR_BYTES + " BLOB, ")
        create.append(REPOSITORY_URL + " TEXT ")
        create.append(" ); ")

        return create.toString()
    }

    fun getContentValues(user: User): ContentValues {
        val values: ContentValues = ContentValues()
        values.put(ID, user.id)
        values.put(IDWEB, user.idWeb)
        values.put(LOGIN, user.login)
        values.put(AVATAR, user.avatar)
        values.put(REPOSITORY_URL, user.repositoryUrl)

        return values
    }

    fun getUser(cursor: Cursor): Any {
        val user: User
        if (!cursor.isBeforeFirst || cursor.moveToNext()) {
            user = User(cursor.getLong(cursor.getColumnIndex(ID)),
                    cursor.getLong(cursor.getColumnIndex(IDWEB)),
                    cursor.getString(cursor.getColumnIndex(AVATAR)),
                    cursor.getString(cursor.getColumnIndex(LOGIN)),
                    cursor.getString(cursor.getColumnIndex(REPOSITORY_URL)))
            return user
        } else {
            return Any()
        }
    }

    fun getUsers(cursor: Cursor): List<User> {
        val users: MutableList<User> = mutableListOf()
        while (cursor.moveToNext()) {
            users.add(getUser(cursor) as User)
        }
        return users
    }
}