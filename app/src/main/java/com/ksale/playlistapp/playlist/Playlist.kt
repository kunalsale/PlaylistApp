package com.ksale.playlistapp.playlist

import com.ksale.playlistapp.R

data class Playlist(val id: String, val imagePlaylist: Int = R.drawable.playlist, val playlist: String, val category: String)
