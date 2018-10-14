package com.github.emilg1101.homework.holder

import android.view.View
import com.github.emilg1101.homework.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryViewHolder(itemView: View) : BaseViewHolder<Country>(itemView) {

    override fun bindViewHolder(item: Country, itemClickListener: (Country) -> Unit) = with(itemView) {
        text_name.text = item.name
        text_iso.text = item.iso.toString()
        text_location.text = item.location
    }
}
