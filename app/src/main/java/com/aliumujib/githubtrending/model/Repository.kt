package com.aliumujib.githubtrending.model

import com.aliumujib.data.model.UserEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 *
 * Represents a repository on the presentation layer
 */

class Repository(val id: Int?, val repoFullName: String,
                 val repoName: String, val repoDescription: String,
                 val user: UserEntity, val starsCount: Int,
                 val language: String)