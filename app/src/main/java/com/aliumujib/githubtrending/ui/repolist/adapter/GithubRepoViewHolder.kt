package com.aliumujib.githubtrending.ui.repolist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.model.Repository

/**
 * A RecyclerView ViewHolder that displays a Github repository.
 */
class GithubRepoViewHolder(view: View)
    : RecyclerView.ViewHolder(view) {
    private val repoFullName: TextView = view.findViewById(R.id.repo_fullname)
    private val repoDescription: TextView = view.findViewById(R.id.repo_description)
    private val language: TextView = view.findViewById(R.id.language)
    private val starCount : TextView = view.findViewById(R.id.star_count)
    private val ownerImage : ImageView = view.findViewById(R.id.owner_image_view)
    private var repository: Repository? = null

    init {
        view.setOnClickListener {

        }
    }

    fun bind(repository: Repository?) {
        this.repository = repository
        this.repoFullName.text = repository?.repoFullName
        this.language.text = repository?.language
        updateScore(repository)
    }

    companion object {
        fun create(parent: ViewGroup): GithubRepoViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.repo_list_item, parent, false)
            return GithubRepoViewHolder(view)
        }
    }

    fun updateScore(item: Repository?) {
        starCount.text = "${item?.starsCount ?: 0} stars"
    }
}