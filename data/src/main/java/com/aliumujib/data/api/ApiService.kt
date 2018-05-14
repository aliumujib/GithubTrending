package com.pick2me.android.data.repositories.services

import com.aliumujib.data.model.SearchResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
//https://api.github.com/search/repositories?q=android+language:java+language:kotlin&sort=stars&order=desc


    @GET("search/repositories")
    fun searchRepositories(@QueryMap headers: Map<String, String>): Observable<SearchResponse>

}
