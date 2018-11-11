package com.github.emilg1101.homework

import android.content.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import com.github.emilg1101.homework.model.Track
import com.github.emilg1101.homework.service.IMusicService
import com.github.emilg1101.homework.service.MusicService
import com.github.emilg1101.homework.utils.Themes
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    private var isPlayed = true

    private var mIRemoteService: IMusicService? = null

    private var receiver: BroadcastReceiver? = null

    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            mIRemoteService = IMusicService.Stub.asInterface(service)
            mIRemoteService?.start(intent.getIntExtra("track_position", 0))
        }

        override fun onServiceDisconnected(className: ComponentName) {
            mIRemoteService = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Themes.getTheme(this))
        setContentView(R.layout.activity_player)

        supportActionBar?.apply {
            title = getString(R.string.activity_player)
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        val musicServiceIntent = Intent(this, MusicService::class.java)
        musicServiceIntent.putExtra("track_position", intent.getIntExtra("track_position", 0))
        startService(musicServiceIntent)
        bindService(musicServiceIntent, mConnection, Context.BIND_AUTO_CREATE)

        val filter = IntentFilter(MusicService.ACTION_PLAY)
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.getParcelableExtra<Track>("track")?.let {
                    text_title.text = it.title
                }
            }
        }
        registerReceiver(receiver, filter)

        setListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        unbindService(mConnection)
    }

    private fun setListeners() {
        button_next.setOnClickListener {
            mIRemoteService?.next()
            isPlayed = true
            button_play.isChecked = true
        }
        button_prev.setOnClickListener {
            mIRemoteService?.prev()
            isPlayed = true
            button_play.isChecked = true
        }
        button_play.setOnClickListener {
            isPlayed = if (isPlayed) {
                mIRemoteService?.pause()
                false
            } else {
                mIRemoteService?.play()
                true
            }
        }
    }
}
