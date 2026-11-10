package com.freehome.smart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial
import com.thingclips.smart.home.sdk.ThingHomeSdk

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // SDK ইনিশিয়ালাইজেশন
        ThingHomeSdk.init(application)
        setContentView(R.layout.activity_main)

        val switchPower = findViewById<SwitchMaterial>(R.id.switchPower)

        switchPower.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "ON" else "OFF"
            Toast.makeText(this, "Device Switched $status", Toast.LENGTH_SHORT).show()
        }
    }
}
