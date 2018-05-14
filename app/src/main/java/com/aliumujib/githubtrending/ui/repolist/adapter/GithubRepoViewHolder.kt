package com.aliumujib.githubtrending.ui.repolist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.aliumujib.githubtrending.R
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.utils.ImageLoader

/**
 * A RecyclerView ViewHolder that displays a Github repository.
 */
class GithubRepoViewHolder(view: View, private var imageLoader: ImageLoader, private val clickCallback: (Repository?) -> Unit)
    : RecyclerView.ViewHolder(view) {
    private val repoFullName: TextView = view.findViewById(R.id.repo_fullname)
    private val repoDescription: TextView = view.findViewById(R.id.repo_description)
    private val language: TextView = view.findViewById(R.id.language)
    private val starCount: TextView = view.findViewById(R.id.star_count)
    private val ownerImage: ImageView = view.findViewById(R.id.owner_image_view)
    private var repository: Repository? = null

    init {
        view.setOnClickListener {
            clickCallback.invoke(repository)
        }
    }

    fun bind(repository: Repository?) {
        this.repository = repository
        this.repoFullName.text = repository?.repoFullName
        this.language.text = repository?.language
        this.repoDescription.text = repository?.repoDescription
        imageLoader.load(repository?.user?.imageUrl!!, ownerImage, true)
        updateScore(repository)
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader, clickCallback: (Repository?) -> Unit): GithubRepoViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.repo_list_item, parent, false)
            return GithubRepoViewHolder(view, imageLoader, clickCallback = clickCallback)
        }
    }

    fun updateScore(item: Repository?) {
        starCount.text = "${item?.starsCount ?: 0} stars"
    }
}