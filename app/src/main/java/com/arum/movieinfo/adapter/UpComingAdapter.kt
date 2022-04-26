package com.arum.movieinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arum.movieinfo.R
import com.arum.movieinfo.model.UpcomingList
import com.arum.movieinfo.model.UpcomingResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_upcoming.view.*

private var upcoming: MutableList<UpcomingList> = mutableListOf()

class UpComingAdapter (val listener : UpComingListener): RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_upcoming, parent, false)
        return UpComingViewHolder(view)
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.bind(upcoming[position])
    }

    override fun getItemCount(): Int {
        return upcoming.size
    }


    inner class UpComingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: UpcomingList) {
            itemView.apply {
                tvUpComing.text = data.original_title


                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w92/" + data.backdrop_path)
                    .into(ivUpComing)
                setOnClickListener {
                    listener.UpComingOnClick(data)
                }
            }
        }

    }

    fun update(data: MutableList<UpcomingList>) {
       upcoming = data
        notifyDataSetChanged()
    }
}

interface UpComingListener {
    fun UpComingOnClick (data : UpcomingList)
}


