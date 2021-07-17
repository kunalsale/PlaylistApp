package com.ksale.playlistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayListViewModel(repository: PlaylistRepository): ViewModel() {
    val playlists = MutableLiveData<Result<List<Playlist>>>()

    init {
        repository.getPlaylists()
    }
}
