package com.ksale.playlistapp.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val playlistService: PlaylistService
) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        return playlistService.fetchPlaylist()
    }
}
