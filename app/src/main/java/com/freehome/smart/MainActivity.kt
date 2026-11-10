package com.freehome.smart

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import com.thingclips.smart.home.sdk.ThingHomeSdk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // SDK ইনিশিয়ালাইজেশন
        try {
            ThingHomeSdk.init(application)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContentView(R.layout.activity_main)

        val switchPower = findViewById<SwitchMaterial>(R.id.switchPower)
        val tvDeviceStatus = findViewById<TextView>(R.id.tvDeviceStatus)

        switchPower.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                tvDeviceStatus.text = "Smart Switch: ON"
                tvDeviceStatus.setTextColor(android.graphics.Color.parseColor("#34D399"))
                Toast.makeText(this, "Device Turned ON (0ms LAN)", Toast.LENGTH_SHORT).show()
            } else {
                tvDeviceStatus.text = "Smart Switch: OFF"
                tvDeviceStatus.setTextColor(android.graphics.Color.WHITE)
                Toast.makeText(this, "Device Turned OFF", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
