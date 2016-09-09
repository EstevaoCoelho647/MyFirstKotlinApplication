package com.example.estevao.myfirstkotlinapplication.model.persistence

import android.util.Log
import com.example.estevao.myfirstkotlinapplication.model.entity.Repository

/**
 * Created by estevao on 09/09/16.
 */

/**
 * Created by estevao on 08/09/16.
 */
object RepositoryRepository {

    fun save(repository: Repository) {
        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.writableDatabase
        val values = RepositoryContract.getContentValues(repository)

        if (repository.id == null) {
            db.insert(RepositoryContract.TABLE, null, values)
        } else {
            val where = RepositoryContract.IDWEB + " = ?"
            val params = arrayOf(repository.idWeb.toString())
            db.update(RepositoryContract.TABLE, values, where, params)
        }
        db.close()
        databaseHelper.close()
    }

    fun save(repositorys: List<Repository>) {
        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.writableDatabase
        db.beginTransaction()
        for (repository in repositorys) {
            Log.e("saveRepository", repository.name)
            val values = RepositoryContract.getContentValues(repository)
            db.insert(RepositoryContract.TABLE, null, values)
        }
        db.setTransactionSuccessful()
        db.endTransaction()
    }

    val all: List<Repository>
        get() {
            val databaseHelper = DatabaseHelper.getInstance()
            val db = databaseHelper.readableDatabase

            val cursor = db.query(RepositoryContract.TABLE, RepositoryContract.columns, null, null, null, null, RepositoryContract.ID)
            val values = RepositoryContract.getRepositories(cursor)

            db.close()
            databaseHelper.close()
            return values
        }

    fun findByIdWeb(id: Long): List<Repository> {
        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.readableDatabase

        val where = RepositoryContract.IDUSER + " = ?"
        val params = arrayOf(id.toString())

        val cursor = db.query(RepositoryContract.TABLE, RepositoryContract.columns, where, params, null, null, null)
        val values = RepositoryContract.getRepositories(cursor)

        db.close()
        databaseHelper.close()
        return values
    }

    fun deleteAll() {
        Log.d("Droping Repositorys", "")

        val databaseHelper = DatabaseHelper.getInstance()
        val db = databaseHelper.readableDatabase

        db.delete(RepositoryContract.TABLE, null, null)

        db.close()
        databaseHelper.close()
    }

}