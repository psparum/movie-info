package com.arum.movieinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arum.movieinfo.R
import com.arum.movieinfo.model.Resultss
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_popular.view.*


private var popular: MutableList<Resultss> = mutableListOf()

class PopularAdapter (val listener : PopularListener): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(popular[position], position)
    }

    override fun getItemCount(): Int {
        return popular.size
    }


    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Resultss, position: Int) {
            itemView.apply {
                tvPopular.text = data.original_title
                tvPopularNumber.text = (position + 1).toString()

                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w92/" + data.poster_path)
                    .into(ivPopular)
                setOnClickListener {
                    listener.PopularOnClick(data)
                }
            }
        }

    }

    fun update(data: MutableList<Resultss>) {
        popular = data
        notifyDataSetChanged()

    }
}

interface PopularListener {
    fun PopularOnClick (data : Resultss)
}


