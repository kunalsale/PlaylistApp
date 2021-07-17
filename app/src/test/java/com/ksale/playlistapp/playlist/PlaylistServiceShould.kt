package com.ksale.playlistapp.playlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import java.lang.RuntimeException

class PlaylistServiceShould {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    val playlistAPI: PlaylistAPI = mock()
    val playlist: List<Playlist> = mock()
    val exception = RuntimeException("Backend error")

    @Test
    fun getPlaylistFromAPI() = runBlockingTest {
        val service = PlaylistService(playlistAPI)
        service.fetchPlaylist().first()
        verify(playlistAPI, times(1)).fetchAllPlaylist()
    }

    @Test
    fun convertValuesToFlowResultAndEmit() = runBlockingTest {
        whenever(playlistAPI.fetchAllPlaylist()).thenReturn(
            playlist
        )
        val service = PlaylistService(playlistAPI)
        assertEquals(Result.success(playlist), service.fetchPlaylist().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest {
        whenever(playlistAPI.fetchAllPlaylist()).thenThrow(
            exception
        )
        val service = PlaylistService(playlistAPI)
        assertEquals("Something went wrong", service.fetchPlaylist().first().exceptionOrNull()!!.message)
    }
}