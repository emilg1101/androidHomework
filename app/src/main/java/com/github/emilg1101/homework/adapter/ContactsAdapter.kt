package com.github.emilg1101.homework.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.holder.BaseViewHolder
import com.github.emilg1101.homework.holder.ContactViewHolder
import com.github.emilg1101.homework.model.Contact

class ContactsAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<BaseViewHolder<Contact>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Contact> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(v)
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: BaseViewHolder<Contact>, position: Int) {
        holder.bindViewHolder(contacts[position])
    }
}
