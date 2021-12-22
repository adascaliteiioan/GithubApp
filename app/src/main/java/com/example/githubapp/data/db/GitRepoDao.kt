package com.example.githubapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GitRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGitRepos(repos: List<GitRepo>)

    @Query("SELECT * FROM git_repo")
    fun getAllRepos(): Flow<List<GitRepo>>
}