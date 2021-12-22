package com.example.githubapp.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubapp.data.db.GitRepoDb
import com.example.githubapp.data.network.GitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(GitApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGitApi(retrofit: Retrofit): GitApi =
        retrofit.create(GitApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): GitRepoDb =
        Room.databaseBuilder(app, GitRepoDb::class.java, "git_repos_db")
            .fallbackToDestructiveMigration()
            .build()
}