package com.aliumujib.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 *  Represents a user on the data layer
 */

@Entity
data class UserEntity(

        @SerializedName("login")  var name: String,

        @SerializedName("avatar_url")  var imageUrl: String,

        @PrimaryKey @SerializedName("id")  var id: Int?

)