package com.ksale.playlistapp.playlist

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
    private val exception = RuntimeException("Something went wrong")

    fun setUpViewModel(): PlayListViewModel {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }
        return PlayListViewModel(repository)
    }

    @Test
    fun getPlaylistFromRepository() = runBlockingTest {
        val viewModel = setUpViewModel()
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitPlaylistFromRepository() = runBlockingTest {
        val viewModel = setUpViewModel()
        System.out.println("PlayListViewModel " + expected)
        System.out.println("PlayListViewModel " + viewModel.playlists.getValueForTest())
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivesError() {
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure<List<Playlist>>(exception))
                }
            )
        }
        val viewModel = PlayListViewModel(repository)
        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }
}