package com.github.emilg1101.homework

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.github.emilg1101.homework.model.Contact
import kotlinx.android.synthetic.main.activity_contact.*
import com.github.emilg1101.homework.utils.Constants.CONTACT_EXTRA

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        supportActionBar?.apply {
            title = getString(R.string.ac_contact_title)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        intent.getParcelableExtra<Contact>(CONTACT_EXTRA)?.let {
            displayContact(it)
        }
    }

    private fun displayContact(contact: Contact) {
        image_contact.setImageDrawable(ContextCompat.getDrawable(this, contact.photo))
        text_name.text = contact.name
        text_email.text = contact.email
        text_phone.text = contact.phone
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
