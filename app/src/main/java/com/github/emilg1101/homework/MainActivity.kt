package com.github.emilg1101.homework

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.github.emilg1101.homework.adapter.TrackAdapter
import com.github.emilg1101.homework.utils.Themes
import com.github.emilg1101.homework.utils.TrackRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Themes.getTheme(this))
        setContentView(R.layout.activity_main)

        list.layoutManager = LinearLayoutManager(this)
        val trackList = TrackRepository.getList()
        list.adapter = TrackAdapter(trackList) {
            val intent = Intent(this, PlayerActivity::class.java)
            intent.putExtra("track_position", trackList.indexOf(it))
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_settings) startActivity(Intent(this, SettingsActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}
