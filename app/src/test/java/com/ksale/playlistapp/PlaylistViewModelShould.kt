package com.ksale.playlistapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.outsideintddexample.acceptancetests.MainCoroutineScopeRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    lateinit var viewModel: PlayListViewModel
    val repository: PlaylistRepository = mock()
    val playlist = mock<List<Playlist>>()
    val expected = Result.success(playlist)

    init {

    }

    @Before
    fun setUp() {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        viewModel = PlayListViewModel(repository)
    }

    @Test
    fun getPlaylistFromRepository() = runBlockingTest {
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitPlaylistFromRepository() = runBlockingTest {
        viewModel = PlayListViewModel(repository)
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }
}