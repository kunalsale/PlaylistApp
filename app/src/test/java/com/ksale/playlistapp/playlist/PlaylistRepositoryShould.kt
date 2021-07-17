package com.ksale.playlistapp.playlist

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PlaylistRepositoryShould {

    private val service: PlaylistService = mock()

    @Test
    fun getPlaylistFromService() {
        val repository = PlaylistRepository(service)
        runBlockingTest {
            repository.getPlaylists()
        }
        verify(service, times(1)).fetchPlaylist()
    }
}