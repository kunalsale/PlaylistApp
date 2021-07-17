package com.ksale.playlistapp.playlist

import retrofit2.http.GET

interface PlaylistAPI {
    @GET("playlist")
    suspend fun fetchAllPlaylist(): List<Playlist>
}
