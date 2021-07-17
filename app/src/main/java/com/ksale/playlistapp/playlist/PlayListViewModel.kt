package com.ksale.playlistapp.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData

class PlayListViewModel(repository: PlaylistRepository): ViewModel() {
    val playlists = liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }
}
