package com.github.emilg1101.homework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.emilg1101.homework.model.Profile
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        supportActionBar?.let { actionBar ->
            actionBar.title = getString(R.string.edit_activity_title)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }

        intent.extras.get(PROFILE_DATA_EXTRA)?.let {
            (it as? Profile)?.let { profile -> displayProfile(profile) }
        }
    }

    private fun saveProfile() {
        val name = name_text_input_layout.editText?.text.toString()
        val email = email_text_input_layout.editText?.text.toString()
        val phone = phone_text_input_layout.editText?.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(PROFILE_DATA_EXTRA, Profile(name, email, phone))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun displayProfile(profile: Profile) {
        name_text_input_layout.editText?.setText(profile.name)
        email_text_input_layout.editText?.setText(profile.email)
        phone_text_input_layout.editText?.setText(profile.phone)
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        setResult(Activity.RESULT_CANCELED, intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        } else if (item?.itemId == R.id.action_save) {
            saveProfile()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_menu, menu)
        return true
    }

    companion object {
        private const val PROFILE_DATA_EXTRA = "profile_data"
    }
}