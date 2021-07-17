package com.ksale.playlistapp.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksale.playlistapp.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A fragment representing a list of Items.
 */
class PlaylistFragment : Fragment() {

    lateinit var viewModel: PlayListViewModel
    lateinit var viewModelFactory: PlayListViewModelFactory
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.29.124:3000/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PlaylistAPI::class.java)
    val playlistService = PlaylistService(api)
    private val repository = PlaylistRepository(playlistService)

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
        viewModelFactory = PlayListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PlayListViewModel::class.java]
    }

    companion object {
        @JvmStatic
        fun newInstance() = PlaylistFragment()
    }
}