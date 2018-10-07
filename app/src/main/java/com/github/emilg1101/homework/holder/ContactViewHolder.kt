package com.github.emilg1101.homework.holder

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.View
import com.github.emilg1101.homework.ContactActivity
import com.github.emilg1101.homework.model.Contact
import com.github.emilg1101.homework.utils.Constants.CONTACT_EXTRA
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactViewHolder(itemView: View) : BaseViewHolder<Contact>(itemView) {

    override fun bindViewHolder(item: Contact) = with(itemView) {
        itemView.setOnClickListener { view ->
            view.context.let {
                val intent = Intent(it, ContactActivity::class.java)
                intent.putExtra(CONTACT_EXTRA, item)
                it.startActivity(intent)
            }
        }

        image_contact.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.photo))
        text_contact_name.text = item.name
    }
}
