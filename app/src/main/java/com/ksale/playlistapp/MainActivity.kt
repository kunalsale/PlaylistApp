package com.ksale.playlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksale.playlistapp.playlist.PlaylistFragment
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = getString(R.string.playlist_screen_name)
        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.container, PlaylistFragment.newInstance()).commit()
        }
    }
}