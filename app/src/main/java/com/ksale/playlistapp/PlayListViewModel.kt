package com.ksale.playlistapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlayListViewModel: ViewModel() {
    val playlists = MutableLiveData<Result<List<Playlist>>>()

}
