package com.deb.geideahomework.localservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.deb.geideahomework.util.Constants

class LocalService : Service() {
    // Binder given to clients
    private val binder = LocalBinder()
    private var time = 5;

    val timer = object : CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            --time
            val intent = Intent()
            intent.action = Constants.ACTION_TIMER
            intent.putExtra(Constants.DATA, time)
            sendBroadcast(intent)
            Log.i("!!!!!!!! time", millisUntilFinished.toString())

        }
        override fun onFinish() {

        }
    }.start()


    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): LocalService = this@LocalService


    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}