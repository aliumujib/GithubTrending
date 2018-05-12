package com.aliumujib.data.model

import com.aliumujib.githubtrending.model.RepositoryEntity
import com.google.gson.annotations.SerializedName

/**
 * Created by aliumujib on 12/05/2018.
 */
data class SearchResponse(
        @SerializedName("total_count") val totalCount: Int?,
        @SerializedName("incomplete_results") val incompleteResults: Boolean?,
        @SerializedName("items") val items: List<RepositoryEntity>?
)