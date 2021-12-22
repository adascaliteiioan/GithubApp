package com.example.githubapp.data.repository

import com.example.githubapp.data.db.GitRepo
import com.example.githubapp.data.db.GitRepoDao
import com.example.githubapp.data.db.GitRepoDb
import com.example.githubapp.data.network.GitApi
import com.example.githubapp.util.Resource
import com.example.githubapp.util.networkBoundResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepository @Inject constructor(
    private val gitApi: GitApi,
    private val gitReposDb: GitRepoDb
) {

    private val reposDao: GitRepoDao = gitReposDb.gitReposDao()

    fun getAllRepos(): Flow<Resource<List<GitRepo>>> =
        networkBoundResult(
            query = {
                reposDao.getAllRepos()
            },
            fetch = {
                val response = gitApi.getGitRepos()
                response.repos
            },
            saveFetchResult = { serverResult ->
                reposDao.insertGitRepos(serverResult.map {
                    GitRepo(
                        id = it.id,
                        name = it.name,
                        url = it.url,
                        stars = it.stars,
                        watchers = it.watchers,
                        forks = it.forks,
                        language = it.language.orEmpty(),
                        subscribers = it.subscribers,
                        ownerUrl = it.owner.url,
                        description = it.description
                    )
                })
            }
        )
}