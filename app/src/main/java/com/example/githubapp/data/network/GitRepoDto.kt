package com.example.githubapp.data.network

import com.google.gson.annotations.SerializedName

data class GitRepoDto(
    val id: Long,
    val name: String,
    val url: String,
    @SerializedName("stargazers_count")
    val stars: Int,
    val watchers: Int,
    val forks: Int,
    val language: String? = null,
    @SerializedName("subscribers_count")
    val subscribers: Int,
    val description: String,
    val owner: Owner
)

data class Owner(@SerializedName("avatar_url") val url: String)
