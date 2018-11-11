package com.github.emilg1101.homework.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import com.github.emilg1101.homework.MainActivity
import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.model.Track
import com.github.emilg1101.homework.utils.TrackRepository

class MusicService : Service() {

    private var player: MediaPlayer? = null

    private val trackList = TrackRepository.getList()
    private var currentTrackPosition = 0

    override fun onBind(intent: Intent): IBinder? {
        return mBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            currentTrackPosition = it.getIntExtra("track_position", 0)
            when (it.action) {
                NOTIFY_PLAY -> {
                    if (player?.isPlaying == true) pause() else play()
                }
                NOTIFY_PREVIOUS -> {
                    prev()
                }
                NOTIFY_NEXT -> {
                    next()
                }
                else -> play(currentTrackPosition)
            }
        }
        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
        stopForeground(true)
    }

    private fun play(position: Int) {
        player?.stop()
        player = MediaPlayer.create(this, trackList[position].raw)
        player?.start()
        player?.isLooping = true

        val intent = Intent(ACTION_PLAY)
        intent.putExtra("track", trackList[position])
        sendBroadcast(intent)
        startNotification(trackList[position], player?.isPlaying == true)
    }

    private fun play() {
        player?.start()
        startNotification(trackList[currentTrackPosition], player?.isPlaying == true)
    }

    private fun pause() {
        if (player?.isPlaying == true) {
            player?.pause()
            startNotification(trackList[currentTrackPosition], player?.isPlaying == true)
        }
    }

    private fun next() {
        if (currentTrackPosition == trackList.size - 1) {
            currentTrackPosition = 0
        } else {
            currentTrackPosition++
        }
        play(currentTrackPosition)
    }

    private fun prev() {
        if (currentTrackPosition == 0) {
            currentTrackPosition = trackList.size
        }
        currentTrackPosition--
        play(currentTrackPosition)
    }

    private fun startNotification(track: Track, played: Boolean = true) {
        val notificationManager = getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager
        val channelId =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel("my_service", "My Background Service")
                } else {
                    ""
                }

        val notIntent = Intent(this, MainActivity::class.java)
        notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendInt = PendingIntent.getActivity(this, 0,
                notIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationView = RemoteViews(applicationContext.packageName, R.layout.notification_player)

        val notification = NotificationCompat.Builder(this, channelId)
                .setContentIntent(pendInt)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setTicker(track.title)
                .build()

        notification.contentView = notificationView
        notification.contentView.setTextViewText(R.id.text_title, track.title)
        if (played) {
            notification.contentView.setImageViewResource(R.id.button_play, R.drawable.ic_pause)
        } else {
            notification.contentView.setImageViewResource(R.id.button_play, R.drawable.ic_play)
        }
        setControlsListeners(notificationView)
        startForeground(101, notification)
        notificationManager.notify(101, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    private fun setControlsListeners(view: RemoteViews) {
        val previous = Intent(this, MusicService::class.java)
        previous.action = NOTIFY_PREVIOUS
        previous.putExtra("track_position", currentTrackPosition)

        val next = Intent(this, MusicService::class.java)
        next.action = NOTIFY_NEXT
        next.putExtra("track_position", currentTrackPosition)

        val play = Intent(this, MusicService::class.java)
        play.action = NOTIFY_PLAY
        play.putExtra("track_position", currentTrackPosition)

        view.setOnClickPendingIntent(R.id.button_play,
                PendingIntent.getService(this, 0, play, PendingIntent.FLAG_UPDATE_CURRENT))

        view.setOnClickPendingIntent(R.id.button_next,
                PendingIntent.getService(this, 0, next, PendingIntent.FLAG_ONE_SHOT))

        view.setOnClickPendingIntent(R.id.button_prev,
                PendingIntent.getService(this, 0, previous, PendingIntent.FLAG_UPDATE_CURRENT))
    }

    private val mBinder = object : IMusicService.Stub() {

        override fun next() {
            this@MusicService.next()
        }

        override fun prev() {
            this@MusicService.prev()
        }

        override fun play() {
            this@MusicService.play()
        }

        override fun pause() {
            this@MusicService.pause()
        }

        override fun start(position: Int) {
            this@MusicService.play(position)
        }
    }

    companion object {
        const val ACTION_PLAY = "action_play"

        const val NOTIFY_NEXT = "notify_next"
        const val NOTIFY_PREVIOUS = "notify_previous"
        const val NOTIFY_PLAY = "notify_play"
    }
}
