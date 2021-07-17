package com.ksale.playlistapp.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistRepositoryShould {

    private val playlists: List<Playlist> = mock()
    private val service: PlaylistService = mock()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistFromService() {
        val repository = PlaylistRepository(service)
        runBlockingTest {
            repository.getPlaylists()
        }
        verify(service, times(1)).fetchPlaylist()
    }

    @Test
    fun emitPlaylistFromService() = runBlockingTest {
        whenever(service.fetchPlaylist()).thenReturn(
            flow {
                emit(Result.success(playlists))
            }
        )

        val repository = PlaylistRepository(service)
        assertEquals(playlists, repository.getPlaylists().first().getOrNull())
    }

    @Test
    fun progogateError() = runBlockingTest {
        whenever(service.fetchPlaylist()).thenReturn(
            flow {
                emit(Result.failure<List<Playlist>>(exception))
            }
        )

        val repository = PlaylistRepository(service)
        assertEquals(exception, repository.getPlaylists().first().exceptionOrNull())
    }
}