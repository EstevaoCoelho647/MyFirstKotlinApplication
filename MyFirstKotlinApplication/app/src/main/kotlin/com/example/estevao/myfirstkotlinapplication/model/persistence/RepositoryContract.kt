package com.example.estevao.myfirstkotlinapplication.model.persistence

import android.content.ContentValues
import android.database.Cursor
import com.example.estevao.myfirstkotlinapplication.model.entity.Repository

/**
 * Created by estevao on 09/09/16.
 */
object RepositoryContract {

    val TABLE: String = "repositorytable"
    val ID: String = "id"
    val IDWEB: String = "idweb"
    val IDUSER: String = "idrepository"
    val NAME: String = "name"
    val STARGAZERS: String = "stargazers"

    val columns: Array<String> = arrayOf(ID, IDWEB, IDUSER, NAME, STARGAZERS)

    fun getCreateTableScript(): String {
        val create: StringBuilder = StringBuilder()

        create.append(" CREATE TABLE " + TABLE)
        create.append(" ( ")
        create.append(ID + " INTEGER PRIMARY KEY, ")
        create.append(IDWEB + " INTEGER, ")
        create.append(IDUSER + " INTEGER, ")
        create.append(NAME + " TEXT, ")
        create.append(STARGAZERS + " TEXT ")
        create.append(" ); ")

        return create.toString()
    }

    fun getContentValues(repository: Repository): ContentValues {
        val values: ContentValues = ContentValues()
        values.put(ID, repository.id)
        values.put(IDWEB, repository.idWeb)
        values.put(IDUSER, repository.idUser)
        values.put(NAME, repository.name)
        values.put(STARGAZERS, repository.stargazers)

        return values
    }

    fun getRepository(cursor: Cursor): Any {
        val repository: Repository
        if (!cursor.isBeforeFirst || cursor.moveToNext()) {
            repository = Repository(cursor.getLong(cursor.getColumnIndex(IDWEB)),
                    cursor.getLong(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(NAME)),
                    cursor.getInt(cursor.getColumnIndex(STARGAZERS)),
                    cursor.getLong(cursor.getColumnIndex(IDUSER)))

            return repository
        } else {
            return Any()
        }
    }

    fun getRepositories(cursor: Cursor): List<Repository> {
        val repositories: MutableList<Repository> = mutableListOf()
        while (cursor.moveToNext()) {
            repositories.add(getRepository(cursor) as Repository)
        }
        return repositories
    }
}