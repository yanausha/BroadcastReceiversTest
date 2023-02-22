package com.example.broadcastreceiverstest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.broadcastreceiverstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this)
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == MyReceiver.ACTION_LOADED) {
                val percent = intent.getIntExtra(MyReceiver.EXTRA_PERCENT, 0)
                binding.progressBar.progress = percent
            }
        }
    }

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
        binding.button.setOnClickListener {
            val intent = MyReceiver.newIntent(++count)
            localBroadcastManager.sendBroadcast(intent)
        }
        val intentFilter = IntentFilter().apply {
            addAction(MyReceiver.ACTION_LOADED)
            addAction(MyReceiver.ACTION_CLICKED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        localBroadcastManager.registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        localBroadcastManager.unregisterReceiver(receiver)
    }
}