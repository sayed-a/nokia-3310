package nostalgia.devices.nokia3310.apps.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import nostalgia.devices.nokia3310.R

fun getBatteryPercent(context : Context?): Int {
    val bm = context?.getSystemService(Context.BATTERY_SERVICE) as? BatteryManager
    return bm?.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) ?: -1
}

@Composable
fun BatteryBar() {
    val context = LocalContext.current
    var batteryLevel by remember { mutableIntStateOf(-1) } // Initial state
    var isCharging by remember { mutableStateOf(false) }

    // DisposableEffect to register and unregister the BroadcastReceiver
    DisposableEffect(key1 = context) {
        val batteryReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                try {
                    intent?.let {
                        val batteryPercentage = getBatteryPercent(context)
                        batteryLevel = when (batteryPercentage) {
                            in 1..25 -> 1
                            in 26..50 -> 2
                            in 51..75 -> 3
                            in 75..100 -> 4
                            else -> 0
                        }

                    val status = it.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                    isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL
                    }
                } catch (e: Exception) {
                    Log.d("STAB", e.message ?: "")
                }
            }
        }

        // Register the receiver
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(batteryReceiver, filter)

        // Unregister the receiver when the composable is disposed
        onDispose {
            context.unregisterReceiver(batteryReceiver)
        }
    }

    BarIndicator(batteryLevel, R.drawable.battery, ltr = true, flashLastBar = isCharging)
}