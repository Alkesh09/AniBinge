package com.alkeshapp.anibinge.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alkeshapp.anibinge.R
import com.alkeshapp.anibinge.data.models.AnimeObject
import com.alkeshapp.anibinge.databinding.TopAnimeLayoutBinding
import com.bumptech.glide.Glide

class TopAnimeAdapter(
    private val animeList: List<AnimeObject>,
    private val listener: OnAnimeItemClickListener
) : RecyclerView.Adapter<TopAnimeAdapter.TopAnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TopAnimeViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.top_anime_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TopAnimeViewHolder, position: Int) {
        val anime = animeList[position]
        holder.binding.title.text = anime.title
        Glide.with(holder.binding.animePoster.context).load(anime.images?.jpg?.imageUrl).into(holder.binding.animePoster)
        holder.binding.noOfEpisodes.text = "Episodes: ${anime.episodes}"
        holder.binding.rating.text = "Rating: ${anime.score}/10"
    }

    override fun getItemCount(): Int {
        return animeList.size
    }

    interface OnAnimeItemClickListener{
        fun onAnimeItemClick(animeId: Int)
    }

    inner class TopAnimeViewHolder(val binding: TopAnimeLayoutBinding) : ViewHolder(binding.root),
        View.OnClickListener {
        init {
            binding.animeCard.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val position = adapterPosition
            listener.onAnimeItemClick(animeList.get(position).malId ?: 0)
        }

    }
}