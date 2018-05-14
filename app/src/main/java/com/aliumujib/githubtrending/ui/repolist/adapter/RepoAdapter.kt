package com.aliumujib.githubtrending.ui.repolist.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.aliumujib.githubtrending.R
import com.aliumujib.constants.NetworkState
import com.aliumujib.githubtrending.model.Repository
import com.aliumujib.githubtrending.utils.ImageLoader

/**
 * A simple adapter implementation that shows Github Repositories.
 */
class RepoAdapter(private val clickCallback: (Repository?) -> Unit, private val retryCallback: () -> Unit, private val imageLoader: ImageLoader) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Repository> = mutableListOf()
    private var networkState: NetworkState? = null
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.repo_list_item -> (holder as GithubRepoViewHolder).bind(list[position])
            R.layout.network_state_item -> (holder as NetworkStateItemViewHolder).bindTo(
                    networkState)
        }
    }

    fun publishChanges(data: List<Repository>) {
        val diffCallback = RepoDiffCallBack(this.list, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.list.clear()
        this.list.addAll(data)

        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(
            holder: RecyclerView.ViewHolder,
            position: Int,
            payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            val item = list[position]
            (holder as GithubRepoViewHolder).updateScore(item)
        } else {
            onBindViewHolder(holder, position)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.repo_list_item -> GithubRepoViewHolder.create(parent, imageLoader, clickCallback)
            R.layout.network_state_item -> NetworkStateItemViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.repo_list_item
        }
    }

    override fun getItemCount(): Int {
        return list.size + if (hasExtraRow()) 1 else 0
    }

    /**
     * Gives the real item count at any time without the extra item for the loader
     * */
     val realItemCount: Int
        get() = list.size

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        private val PAYLOAD_SCORE = Any()

        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {
            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                    oldItem.id == newItem.id

            override fun getChangePayload(oldItem: Repository, newItem: Repository): Any? {
                return if (sameExceptStarCount(oldItem, newItem)) {
                    PAYLOAD_SCORE
                } else {
                    null
                }
            }
        }

        private fun sameExceptStarCount(oldItem: Repository, newItem: Repository): Boolean {
            // No need for a deep copy, just need to update the star count
            return oldItem.copy(starsCount = newItem.starsCount) == newItem
        }
    }
}