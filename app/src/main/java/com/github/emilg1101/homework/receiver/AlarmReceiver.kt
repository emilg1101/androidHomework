package com.github.emilg1101.homework.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.github.emilg1101.homework.provider.NotificationProvider

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let { NotificationProvider.provideNotification(context) }
    }
}
