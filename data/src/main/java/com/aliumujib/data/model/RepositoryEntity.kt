package com.aliumujib.githubtrending.model

import android.arch.persistence.room.Entity
import com.aliumujib.data.model.UserEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 * Represents a repository on the presentation layer
 */

@Entity
class RepositoryEntity(@SerializedName("id") val id: Int?, @SerializedName("full_name") val repoFullName: String,
                       @SerializedName("name") val repoName: String, @SerializedName("description") val repoDescription: String,
                       @SerializedName("owner") val user: UserEntity, @SerializedName("stargazers_count") val starsCount: Int,
                       @SerializedName("language") val language: String)