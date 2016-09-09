package com.example.estevao.myfirstkotlinapplication.model.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by estevao on 06/09/16.
 */
data class User(@SerializedName("") var id: Long?, @SerializedName("id") var idWeb: Long, @SerializedName("avatar_url") var avatar: String,
                var login: String, @SerializedName("repos_url") var repositoryUrl: String) : Parcelable {
    constructor(source: Parcel) : this(source.readLong(), source.readLong(), source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id ?: 0)
        dest.writeLong(idWeb)
        dest.writeString(avatar)
        dest.writeString(login)
        dest.writeString(repositoryUrl)
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}
