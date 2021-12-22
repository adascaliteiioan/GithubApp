package com.example.githubapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "git_repo")
data class GitRepo(
    @PrimaryKey val id: Long,
    val name: String,
    val url: String,
    val stars: Int,
    val watchers: Int,
    val forks: Int,
    val language: String,
    val subscribers: Int,
    val ownerUrl: String,
    val description: String
)