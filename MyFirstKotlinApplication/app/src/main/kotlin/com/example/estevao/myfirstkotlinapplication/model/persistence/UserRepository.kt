package com.example.estevao.myfirstkotlinapplication.model.persistence

import android.util.Log
import com.example.estevao.myfirstkotlinapplication.model.entity.User

/**
 * Created by estevao on 08/09/16.
 */
object UserRepository {

    fun save(user: User) {
        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.writableDatabase
        val values = UserContract.getContentValues(user)

        if (user.id == null) {
            db.insert(UserContract.TABLE, null, values)
        } else {
            val where = UserContract.IDWEB + " = ?"
            val params = arrayOf(user.idWeb.toString())
            db.update(UserContract.TABLE, values, where, params)
        }
        db.close()
        databaseHelper.close()
    }

    fun save(users: List<User>) {
        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.writableDatabase
        db.beginTransaction()
        for (user in users) {
            Log.e("saveUser", user.login)
            val values = UserContract.getContentValues(user)
            db.insert(UserContract.TABLE, null, values)
        }
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    val all: List<User>
        get() {
            val databaseHelper = DatabaseHelper.getInstance()
            val db = databaseHelper.readableDatabase

            val cursor = db.query(UserContract.TABLE, UserContract.columns, null, null, null, null, UserContract.ID)
            val values = UserContract.getUsers(cursor)

            db.close()
            databaseHelper.close()
            return values
        }

    fun findByIdWeb(id: Long): List<User> {
        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.readableDatabase

        val where = UserContract.IDWEB + " = ?"
        val params = arrayOf(id.toString())

        val cursor = db.query(UserContract.TABLE, UserContract.columns, where, params, null, null, null)
        val values = UserContract.getUsers(cursor)

        db.close()
        databaseHelper.close()
        return values
    }

    fun deleteAll() {
        Log.d("Droping Users", "")

        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.readableDatabase

        db.delete(UserContract.TABLE, null, null)

        db.close()
        databaseHelper.close()
    }

}