package com.github.emilg1101.homework

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.emilg1101.homework.adapter.ContactsAdapter
import com.github.emilg1101.homework.model.Contact
import com.github.emilg1101.homework.utils.Constants.CONTACT_EXTRA
import com.github.emilg1101.homework.utils.ContactsGenerator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.ac_main_title)

        val contacts = ContactsGenerator.generate()

        rv_contacts_list.layoutManager = LinearLayoutManager(this)
        rv_contacts_list.adapter = ContactsAdapter(contacts) { contactItemClick(it) }
    }

    private fun contactItemClick(contact: Contact) {
        val intent = Intent(this, ContactActivity::class.java)
        intent.putExtra(CONTACT_EXTRA, contact)
        startActivity(intent)
    }
}
