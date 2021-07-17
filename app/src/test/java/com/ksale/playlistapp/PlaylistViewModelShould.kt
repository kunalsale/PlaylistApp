package com.ksale.playlistapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest

class PlaylistViewModelShould {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    val viewModel: PlayListViewModel
    val repository: PlaylistRepository = mock()

    init {
        viewModel = PlayListViewModel()
    }

    @Test
    fun getPlaylistFromRepository() {
        viewModel.playlists.getValueForTest()
        verify(repository, times(1)).getPlaylists()
    }
}