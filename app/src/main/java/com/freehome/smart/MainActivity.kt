package com.freehome.smart

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import com.thingclips.smart.home.sdk.ThingHomeSdk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // সেফ ইনিশিয়ালাইজেশন
        try {
            ThingHomeSdk.init(application)
        } catch (e: Throwable) {
            e.printStackTrace()
        }

        try {
            setContentView(R.layout.activity_main)

            val switchPower = findViewById<SwitchMaterial>(R.id.switchPower)
            val tvDeviceStatus = findViewById<TextView>(R.id.tvDeviceStatus)

            switchPower?.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    tvDeviceStatus?.text = "Smart Switch: ON"
                    tvDeviceStatus?.setTextColor(Color.parseColor("#34D399"))
                    Toast.makeText(this, "Device Turned ON", Toast.LENGTH_SHORT).show()
                } else {
                    tvDeviceStatus?.text = "Smart Switch: OFF"
                    tvDeviceStatus?.setTextColor(Color.WHITE)
                    Toast.makeText(this, "Device Turned OFF", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Throwable) {
            // লেআউট লোড না হলেও অ্যাপ যেন ক্র্যাশ না করে
            val fallbackText = TextView(this).apply {
                text = "FreeHome Smart\nSystem Ready!\n\nError: ${e.localizedMessage}"
                setTextColor(Color.WHITE)
                textSize = 18f
                setPadding(50, 150, 50, 50)
                setBackgroundColor(Color.parseColor("#0F172A"))
            }
            setContentView(fallbackText)
        }
    }
}
