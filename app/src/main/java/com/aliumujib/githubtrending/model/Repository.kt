package com.aliumujib.githubtrending.model

import android.os.Parcel
import android.os.Parcelable
import com.aliumujib.data.model.UserEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 * Represents a repository on the presentation layer
 */

data class Repository(val id: Int?, val repoFullName: String,
                      val repoName: String, val repoDescription: String,
                      val user: User, val starsCount: Int,
                      val language: String?, var webURL: String = "https://github.com/" + repoFullName) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(repoFullName)
        parcel.writeString(repoName)
        parcel.writeString(repoDescription)
        parcel.writeParcelable(user, flags)
        parcel.writeInt(starsCount)
        parcel.writeString(language)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Repository

        if (id != other.id) return false
        if (repoFullName != other.repoFullName) return false
        if (repoName != other.repoName) return false
        if (repoDescription != other.repoDescription) return false
        if (user != other.user) return false
        if (starsCount != other.starsCount) return false
        if (language != other.language) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + repoFullName.hashCode()
        result = 31 * result + repoName.hashCode()
        result = 31 * result + repoDescription.hashCode()
        result = 31 * result + user.hashCode()
        result = 31 * result + starsCount
        return result
    }

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }


}