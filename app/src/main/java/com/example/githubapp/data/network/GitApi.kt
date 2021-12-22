package com.example.githubapp.data.network

import retrofit2.http.GET

interface GitApi {

    companion object {
        const val BASE_URL = "  https://api.github.com/"
    }

    @GET("search/repositories?q=android&sort=stars&order=desc")
    suspend fun getGitRepos(): ReposResponse
}