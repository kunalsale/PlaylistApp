package com.ksale.playlistapp.playlist

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ViewModelScoped
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    fun playlistAPI(retrofit: Retrofit): PlaylistAPI = retrofit.create(PlaylistAPI::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.29.124:3000/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}