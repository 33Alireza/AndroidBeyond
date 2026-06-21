package com.example.androidbeyond.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings

class AiPlaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            val isTurnedOn = Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            ) != 0
            println("Is airplane mode on? $isTurnedOn")
        }
    }
}