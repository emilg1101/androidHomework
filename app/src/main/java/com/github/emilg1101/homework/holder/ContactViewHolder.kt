package com.github.emilg1101.homework.holder

import android.support.v4.content.ContextCompat
import android.view.View
import com.github.emilg1101.homework.model.Contact
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactViewHolder(itemView: View) : BaseViewHolder<Contact>(itemView) {

    override fun bindViewHolder(item: Contact, itemClickListener: (Contact) -> Unit) = with(itemView) {
        itemView.setOnClickListener { itemClickListener(item) }
        image_contact.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.photo))
        text_contact_name.text = item.name
    }
}
