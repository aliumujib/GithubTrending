package com.aliumujib.githubtrending.model

<<<<<<< HEAD
import android.os.Parcel
import android.os.Parcelable
=======
>>>>>>> b8a409618f2277284511ab58c00096fbee42ea2a
import com.aliumujib.data.model.UserEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 * Represents a repository on the presentation layer
 */

class Repository(val id: Int?, val repoFullName: String,
                 val repoName: String, val repoDescription: String,
<<<<<<< HEAD
                 val user: User, val starsCount: Int,
                 val language: String) : Parcelable {
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

    companion object CREATOR : Parcelable.Creator<Repository> {
        override fun createFromParcel(parcel: Parcel): Repository {
            return Repository(parcel)
        }

        override fun newArray(size: Int): Array<Repository?> {
            return arrayOfNulls(size)
        }
    }

}
=======
                 val user: UserEntity, val starsCount: Int,
                 val language: String)
>>>>>>> b8a409618f2277284511ab58c00096fbee42ea2a
