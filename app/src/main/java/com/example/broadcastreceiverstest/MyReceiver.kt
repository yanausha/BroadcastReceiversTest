package com.example.broadcastreceiverstest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            ACTION_CLICKED -> {
                val count = intent.getIntExtra(EXTRA_COUNT, 0)
                Toast.makeText(
                    context,
                    "Clicked $count",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Intent.ACTION_BATTERY_LOW -> {
                Toast.makeText(
                    context,
                    "Battery low",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                val turnOn = intent.getBooleanExtra("state", false)
                Toast.makeText(
                    context,
                    "Airplane mode. Turn on: $turnOn",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {

        const val ACTION_CLICKED = "clicked"
        const val ACTION_LOADED = "loaded"
        private const val EXTRA_COUNT = "count"
        const val EXTRA_PERCENT = "percent"

        fun newIntent(count: Int): Intent {
            return Intent(ACTION_CLICKED).putExtra(EXTRA_COUNT, count)
        }
    }
}