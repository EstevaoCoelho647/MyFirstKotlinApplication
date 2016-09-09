package com.example.estevao.myfirstkotlinapplication.model.persistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.estevao.myfirstkotlinapplication.ApplicationUtil

/**
 * Created by estevao on 06/09/16.
 */
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "apiGit", null, 1) {

    companion object {
        fun getInstance(): DatabaseHelper {
            return DatabaseHelper(ApplicationUtil.context!!)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(UserContract.getCreateTableScript())
        p0.execSQL(RepositoryContract.getCreateTableScript())
    }

}