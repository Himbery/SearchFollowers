package com.test.searchfollowerstest

import java.util.Date

data class ItemRepo(
    var name: String,
    var description: String,
    var forks_count: Int,
    var stargazers_count: Int,
    var default_branch: String,
    var language: String,
    var updated_at: String
)
