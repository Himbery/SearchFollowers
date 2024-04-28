package com.test.searchfollowerstest

import retrofit2.http.GET
import retrofit2.http.Query

interface FollowersApi {

    @GET("/search/users")
    suspend fun getFollowers(@Query("q") query: String)
}