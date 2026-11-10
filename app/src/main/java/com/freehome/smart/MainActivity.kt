package com.freehome.smart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.switchmaterial.SwitchMaterial
import com.thingclips.smart.home.sdk.ThingHomeSdk

data class SmartDevice(
    val id: String,
    val name: String,
    val room: String,
    val icon: String,
    var isPowerOn: Boolean,
    var isLocal: Boolean,
    var statusText: String
)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try { ThingHomeSdk.init(application) } catch (e: Exception) {}
        setContentView(R.layout.activity_main)

        val rvDevices = findViewById<RecyclerView>(R.id.rvDevices)
        rvDevices.layoutManager = GridLayoutManager(this, 2)

        val deviceList = mutableListOf(
            SmartDevice("1", "Main Light", "Living Room", "💡", true, true, "ON (85%)"),
            SmartDevice("2", "AC Unit", "Bedroom", "❄️", true, true, "24°C Cool"),
            SmartDevice("3", "Water Heater", "Bathroom", "⚡", false, true, "OFF"),
            SmartDevice("4", "Living Socket", "Living Room", "🔌", true, false, "ON")
        )

        val adapter = DeviceAdapter(deviceList) { device ->
            showDeviceControlBottomSheet(device)
        }
        rvDevices.adapter = adapter
    }

    private fun showDeviceControlBottomSheet(device: SmartDevice) {
        val dialog = BottomSheetDialog(this)
        val sheetView = layoutInflater.inflate(R.layout.dialog_device_control, null)

        val title = sheetView.findViewById<TextView>(R.id.dialogTitle)
        val btnBigPower = sheetView.findViewById<Button>(R.id.btnBigPower)
        val seekBrightness = sheetView.findViewById<SeekBar>(R.id.seekBrightness)

        title.text = device.name

        btnBigPower.setOnClickListener {
            device.isPowerOn = !device.isPowerOn
            Toast.makeText(this, "${device.name} Power: ${if (device.isPowerOn) "ON" else "OFF"}", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.setContentView(sheetView)
        dialog.show()
    }
}

class DeviceAdapter(
    private val devices: List<SmartDevice>,
    private val onItemClick: (SmartDevice) -> Unit
) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIcon: TextView = view.findViewById(R.id.tvIcon)
        val tvName: TextView = view.findViewById(R.id.tvDeviceName)
        val tvRoom: TextView = view.findViewById(R.id.tvDeviceRoom)
        val tvBadge: TextView = view.findViewById(R.id.tvBadge)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val cardSwitch: SwitchMaterial = view.findViewById(R.id.cardSwitch)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_device_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = devices[position]
        holder.tvIcon.text = item.icon
        holder.tvName.text = item.name
        holder.tvRoom.text = item.room
        holder.tvStatus.text = item.statusText
        holder.cardSwitch.isChecked = item.isPowerOn

        holder.tvBadge.text = if (item.isLocal) "LAN 0ms" else "WAN Cloud"

        holder.cardSwitch.setOnCheckedChangeListener { _, isChecked ->
            item.isPowerOn = isChecked
            item.statusText = if (isChecked) "ON" else "OFF"
            holder.tvStatus.text = item.statusText
        }

        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = devices.size
}
