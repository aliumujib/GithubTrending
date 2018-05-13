package com.aliumujib.githubtrending.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by aliumujib on 12/05/2018.
 *
 *  Represents a user on the presentation layer
 */
data class User constructor(private val name: String, private val imageUrl: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}