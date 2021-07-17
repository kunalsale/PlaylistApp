package com.ksale.playlistapp.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PlaylistService(private val playlistAPI: PlaylistAPI) {
    fun fetchPlaylist(): Flow<Result<List<Playlist>>> {
        return flow {
            emit(Result.success(playlistAPI.fetchAllPlaylist()))
        }.catch {
            emit(Result.failure<List<Playlist>>(RuntimeException("Something went wrong")))
        }
    }
}
