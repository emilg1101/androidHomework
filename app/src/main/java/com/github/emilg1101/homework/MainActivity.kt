package com.github.emilg1101.homework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.github.emilg1101.homework.model.Profile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var profile: Profile = Profile()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = getString(R.string.main_activity_title)
    }

    private fun displayProfile(profile: Profile) {
        this.profile = profile
        text_name.text = profile.name
        text_email.text = profile.email
        text_phone.text = profile.phone
    }

    private fun shareProfile(profile: Profile) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, profile.toString())
            type = "text/plain"
        }
        startActivityForResult(sendIntent, SHARE_REQUEST_CODE)
    }

    private fun editProfile(profile: Profile) {
        val editIntent = Intent(this, EditActivity::class.java)
        editIntent.putExtra(PROFILE_DATA_EXTRA, profile)
        startActivityForResult(editIntent, EDIT_REQUEST_CODE)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_share -> shareProfile(profile)
            R.id.action_edit -> editProfile(profile)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == EDIT_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.extras?.get(PROFILE_DATA_EXTRA)?.let {
                    (it as? Profile)?.let { profile ->
                        displayProfile(profile)
                        Toast.makeText(this, getString(R.string.ac_main_msg_profile_saved), Toast.LENGTH_SHORT).show()
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.ac_main_msg_editing_canceled), Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == SHARE_REQUEST_CODE) {
            Toast.makeText(this, getString(R.string.ac_main_msg_profile_shared), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val EDIT_REQUEST_CODE = 123
        private const val SHARE_REQUEST_CODE = 321

        private const val PROFILE_DATA_EXTRA = "profile_data"
    }
}
