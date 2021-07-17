package com.ksale.playlistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayListViewModel(repository: PlaylistRepository): ViewModel() {
    val playlists = MutableLiveData<Result<List<Playlist>>>()

    init {
        viewModelScope.launch {
            repository.getPlaylists()
                .collect {
                    playlists.value = it
                }
        }
    }
}
