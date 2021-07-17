package com.ksale.playlistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A fragment representing a list of Items.
 */
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playlist_list, container, false)
        setUpViewModel()
        viewModel.playlists.observe(this, { playlists ->
            val list = playlists.getOrNull()
            if(list != null) {
                setUpList(view, list)
            }
            else {
                TODO()
            }
        })
        return view
    }

    private fun setUpList(
        view: View?,
        playlists: List<Playlist>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = PlaylistRecyclerAdapter(playlist = playlists)
        }
    }

    private fun setUpViewModel() {
        viewModelFactory = PlayListViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }
}