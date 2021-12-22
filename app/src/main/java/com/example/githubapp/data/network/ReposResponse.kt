package com.example.githubapp.data.network

import com.google.gson.annotations.SerializedName

data class ReposResponse(
    @SerializedName("items")
    val repos: List<GitRepoDto>
)