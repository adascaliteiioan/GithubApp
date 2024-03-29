package com.example.githubapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GitRepo::class], version = 2, exportSchema = false)
abstract class GitRepoDb : RoomDatabase() {

    abstract fun gitReposDao(): GitRepoDao
}