package com.ksale.playlistapp

import android.graphics.drawable.Drawable

data class Playlist(val id: String, val imagePlaylist: Int = R.drawable.playlist, val playlist: String, val category: String)
