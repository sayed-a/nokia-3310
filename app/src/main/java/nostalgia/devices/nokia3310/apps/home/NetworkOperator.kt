package nostalgia.devices.nokia3310.apps.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun NetworkOperator() {
    val context = LocalContext.current
    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    var networkOperatorName by remember { mutableStateOf("Unknown") }
    var permissionGranted by remember {
        mutableStateOf(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionGranted = permissions.values.all { it } // Check if all requested permissions are granted
        if (permissionGranted) {
            // Permissions granted, you might want to trigger listener registration here
            // if it wasn't already attempted or if you have a refresh mechanism.
        } else {
            networkOperatorName = "Unknown Network"
        }
    }

    DisposableEffect(key1 = telephonyManager) {
        if (!permissionGranted) {
            networkOperatorName = "Unknown Network"
        } else {
            networkOperatorName = telephonyManager.networkOperatorName
        }

        onDispose { }
    }

    // Lifecycle observer to request permissions when the Composable enters the Resumed state
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, permissionLauncher, permissionGranted) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (!permissionGranted) {
                    permissionLauncher.launch(arrayOf(Manifest.permission.READ_PHONE_STATE))
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Text(networkOperatorName)
}