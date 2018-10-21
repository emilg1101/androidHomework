package com.github.emilg1101.homework

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TimePicker
import com.github.emilg1101.homework.receiver.AlarmReceiver
import com.github.emilg1101.homework.utils.toTimeFormat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 8, 0)

        button_set_time.setOnClickListener { showTimePickerDialog() }
        button_start.setOnClickListener { startAlarm() }
        button_stop.setOnClickListener { stopAlarm() }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        text_time.text = "${hourOfDay.toTimeFormat()}:${minute.toTimeFormat()}"
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay, minute)
    }

    private fun startAlarm() {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 100, intent, FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    private fun stopAlarm() {
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 100, intent, FLAG_UPDATE_CURRENT)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    private fun showTimePickerDialog() {
        TimePickerDialog(this, this,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true).show()
    }
}
