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

        supportActionBar?.apply {
            title = getString(R.string.edit_activity_title)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        intent.extras.get(PROFILE_DATA_EXTRA)?.let { it ->
            (it as? Profile)?.let { displayProfile(it) }
        }
    }

    private fun saveProfile() {
        val name = input_name.editText?.text.toString()
        val email = input_email.editText?.text.toString()
        val phone = input_phone.editText?.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(PROFILE_DATA_EXTRA, Profile(name, email, phone))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun displayProfile(profile: Profile) {
        input_name.editText?.setText(profile.name)
        input_email.editText?.setText(profile.email)
        input_phone.editText?.setText(profile.phone)
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
