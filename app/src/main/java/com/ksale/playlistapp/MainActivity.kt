package com.ksale.playlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ksale.playlistapp.playlist.PlaylistFragment

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