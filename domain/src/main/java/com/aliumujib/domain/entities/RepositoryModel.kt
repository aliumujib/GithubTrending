package com.aliumujib.domain.entities

/**
 * Created by aliumujib on 12/05/2018.
 *
 *
 * Represents a repository on the domain layer
 */
class RepositoryModel(val id: Int?, val repoFullName: String,
                      val repoName: String, val repoDescription: String,
                      val user: UserModel, val starsCount: Int,
                      val language: String)