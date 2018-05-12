package com.aliumujib.githubtrending.model

/**
 * Created by aliumujib on 12/05/2018.
 *
 * Represents a repository on the presentation layer
 */

class Repository(val repoName: String, val repoImage: String, val repoDescription: String,
                 val user: User, val starsCount: Int, val language: String)