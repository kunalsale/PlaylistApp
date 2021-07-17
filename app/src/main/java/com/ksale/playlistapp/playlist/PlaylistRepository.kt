package com.ksale.playlistapp.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistRepository(
    private val playlistService: PlaylistService
) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        playlistService.fetchPlaylist()
        return flow {  }
    }
}
