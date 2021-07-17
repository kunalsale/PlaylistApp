package com.ksale.playlistapp.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ksale.playlistapp.databinding.FragmentPlaylistBinding

class PlaylistRecyclerAdapter(
    private val playlist: List<Playlist>
) : RecyclerView.Adapter<PlaylistRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentPlaylistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = playlist[position]
        holder.tvPlaylist.text = item.playlist
        holder.tvCategory.text = item.category
        holder.imgPlaylist.background = ContextCompat.getDrawable(holder.itemView.context, item.imagePlaylist)
    }

    override fun getItemCount(): Int = playlist.size

    inner class ViewHolder(binding: FragmentPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imgPlaylist: ImageView = binding.imgPlaylist
        val tvPlaylist: TextView = binding.tvPlaylist
        val tvCategory: TextView = binding.tvCategory

        override fun toString(): String {
            return super.toString() + " '" + tvPlaylist.text + "'"
        }
    }
}