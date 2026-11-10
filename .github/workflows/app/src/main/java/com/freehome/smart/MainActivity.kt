package com.freehome.smart

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.thingclips.smart.home.sdk.ThingHomeSdk

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // SDK ইনিশিয়ালাইজেশন
        ThingHomeSdk.init(application)
        
        val textView = TextView(this).apply {
            text = "FreeHome Smart - Ready!\nNo Subscription | No AI"
            textSize = 20f
            setPadding(50, 100, 50, 50)
        }
        setContentView(textView)
    }
}
