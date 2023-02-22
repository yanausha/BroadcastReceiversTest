package com.example.broadcastreceiverstest

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcastreceiverstest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var count = 0

    private val receiver = MyReceiver()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Intent(this, MyService::class.java).apply {
            startService(this)
        }
        binding.button.setOnClickListener {
            val intent = MyReceiver.newIntent(++count)
            sendBroadcast(intent)
        }
        val intentFilter = IntentFilter().apply {
            addAction(MyReceiver.ACTION_LOADED)
            addAction(MyReceiver.ACTION_CLICKED)
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(receiver, intentFilter)


    }
}