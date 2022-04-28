package com.arum.movieinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arum.movieinfo.R
import com.arum.movieinfo.model.NowPlayingList
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_nowplaying.view.*


private var nowplaying: MutableList<NowPlayingList> = mutableListOf()

class NowPlayingAdapter (val listener : NowPlayingListener): RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_nowplaying, parent, false)
        return NowPlayingViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(nowplaying[position])
    }

    override fun getItemCount(): Int {
        return nowplaying.size
    }


    inner class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: NowPlayingList) {
            itemView.apply {
                tvNowPlaying.text = data.original_title


                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w92/" + data.backdrop_path)
                    .into(ivNowPlaying)
                setOnClickListener {
                    listener.NowPlayingOnClick(data)
                }
            }
        }

    }

    fun update(data: MutableList<NowPlayingList>) {
        nowplaying = data
        notifyDataSetChanged()

    }
}

interface NowPlayingListener {
    fun NowPlayingOnClick (data : NowPlayingList)
}


