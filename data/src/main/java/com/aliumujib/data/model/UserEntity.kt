package com.aliumujib.data.model

import android.arch.persistence.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 *  Represents a user on the presentation layer
 */

@Entity
class UserEntity(@SerializedName("login") val name: String,
                 @SerializedName("avatar_url") val imageUrl: String,
                 @SerializedName("id") val id: Int?)