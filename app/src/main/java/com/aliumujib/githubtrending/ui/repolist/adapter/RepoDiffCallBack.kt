package com.aliumujib.githubtrending.ui.repolist.adapter

import android.support.v7.util.DiffUtil
import com.aliumujib.githubtrending.model.Repository

/**
 * Created by aliumujib on 13/05/2018.
 */

class RepoDiffCallBack(private val oldList: List<Repository>,
                       private val newList: List<Repository>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}