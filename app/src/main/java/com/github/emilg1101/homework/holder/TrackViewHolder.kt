package com.github.emilg1101.homework.holder

import android.view.View
import com.github.emilg1101.homework.model.Track
import kotlinx.android.synthetic.main.item_track.view.*

class TrackViewHolder(itemView: View) : BaseViewHolder<Track>(itemView) {

    override fun bindViewHolder(item: Track, itemClickListener: (Track) -> Unit) = with(itemView) {
        itemView.setOnClickListener { itemClickListener(item) }
        text_title.text = item.title
    }
}
