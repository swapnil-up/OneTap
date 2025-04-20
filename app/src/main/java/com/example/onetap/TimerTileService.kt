package com.example.onetap

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.service.quicksettings.TileService
import android.widget.Toast

class TimerTileService: TileService() {
    override fun onClick() {
        Toast.makeText(this, "15 minute timer started", Toast.LENGTH_SHORT).show()
        scheduleBeep()
    }

    @SuppressLint("MissingPermission")
    private fun scheduleBeep() {
        val intent = Intent(this, BeepReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val triggerTime = System.currentTimeMillis()+15*60*1000
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent
        )
    }
}