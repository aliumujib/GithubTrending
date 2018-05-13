package com.aliumujib.githubtrending.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.aliumujib.data.model.UserEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 * Represents a repository on the data layer
 */

@Entity(tableName = "REPOSITORIES")
data class RepositoryEntity (

        @PrimaryKey @SerializedName("id") var id: Int,

        @SerializedName("full_name") var repoFullName: String,

        @SerializedName("name") var repoName: String,

        @SerializedName("description") var repoDescription: String,

        @Ignore @SerializedName("owner") var user: UserEntity?,

        @SerializedName("stargazers_count") var starsCount: Int,

        @SerializedName("language") var language: String

){

    constructor() : this(0, "", "", "", null, 0, "")

}