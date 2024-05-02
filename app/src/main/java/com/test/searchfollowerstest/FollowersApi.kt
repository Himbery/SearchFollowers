package com.test.searchfollowerstest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface FollowersApi {

    @GET("/search/users")
    fun getUsers(@Query("q") query: String): Call<ItemList>

    @GET()
    fun loadFollowers(@Url url: String): Call<List<ItemFollowers>>

    @GET
    fun loadRepos(@Url url : String) : Call<List<ItemRepo>>
}