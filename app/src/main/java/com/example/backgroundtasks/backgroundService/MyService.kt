package com.example.backgroundtasks.backgroundService

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import androidx.annotation.Nullable

class MyService : Service() {
    //creating a media player object
    private var player: MediaPlayer? = null

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        //getting systems default ringtone
        player = MediaPlayer.create(
            this,
            Settings.System.DEFAULT_RINGTONE_URI
        )
        //setting loop play to true
        //this will make the ringtone continuously playing
        player!!.isLooping = true

        //staring the player
        player!!.start()

        //start sticky means service will be explicity started and stopped
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        //stopping the player when service is destroyed
        player!!.stop()
    }
}