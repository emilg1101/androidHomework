package com.github.emilg1101.homework

import android.os.Bundle
import android.view.MenuItem
import com.github.emilg1101.homework.utils.AppCompatPreferenceActivity
import com.github.emilg1101.homework.utils.Themes

class SettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Themes.getTheme(this))
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
