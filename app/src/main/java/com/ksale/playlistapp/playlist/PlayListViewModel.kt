package com.ksale.playlistapp.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import javax.inject.Inject

class PlayListViewModel @Inject constructor(repository: PlaylistRepository): ViewModel() {
    val playlists = liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }
}
