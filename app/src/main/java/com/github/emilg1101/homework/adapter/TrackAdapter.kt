package com.github.emilg1101.homework.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.holder.BaseViewHolder
import com.github.emilg1101.homework.holder.TrackViewHolder
import com.github.emilg1101.homework.model.Track

class TrackAdapter(trackList: List<Track>, private val itemClickListener: (Track) -> Unit) : RecyclerView.Adapter<BaseViewHolder<Track>>() {

    private var list = mutableListOf<Track>()

    init {
        this.list.addAll(trackList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Track> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<Track>, position: Int) {
        holder.bindViewHolder(list[position], itemClickListener)
    }
}
