package nostalgia.devices.nokia3310.apps.common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.PhoneStateListener
import android.telephony.SignalStrength
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import nostalgia.devices.nokia3310.R

@Composable
fun SignalBar() {
    val context = LocalContext.current
    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    var signalStrengthLevel by remember { mutableStateOf(0) }
    var permissionGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionGranted = permissions.values.all { it } // Check if all requested permissions are granted
        if (permissionGranted) {
            // Permissions granted, you might want to trigger listener registration here
            // if it wasn't already attempted or if you have a refresh mechanism.
        } else {
            signalStrengthLevel = 0
        }
    }

    // Register and unregister the listener using DisposableEffect for lifecycle safety
    DisposableEffect(key1 = telephonyManager, key2 = permissionGranted) {
        if (!permissionGranted) {
            // If permissions are not granted, don't attempt to register
            onDispose { } // No-op dispose
        } else {
            val signalUpdateCallback: (Int) -> Unit = { newSignalStrength ->
                signalStrengthLevel = newSignalStrength
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val callback =
                    object : TelephonyCallback(), TelephonyCallback.SignalStrengthsListener {
                        override fun onSignalStrengthsChanged(signalStrength: SignalStrength) {
                            signalUpdateCallback(signalStrength.level)
                        }
                    }
                telephonyManager.registerTelephonyCallback(context.mainExecutor, callback)
                onDispose {
                    telephonyManager.unregisterTelephonyCallback(callback)
                }
            } else {
                // Use PhoneStateListener for older versions
                @Suppress("DEPRECATION")
                val phoneStateListener = object : PhoneStateListener() {
                    @Deprecated("Deprecated in API 31")
                    override fun onSignalStrengthsChanged(signalStrength: SignalStrength?) {
                        super.onSignalStrengthsChanged(signalStrength)
                        signalStrength?.let { signalUpdateCallback(it.level) }
                    }
                }
                @Suppress("DEPRECATION")
                telephonyManager.listen(
                    phoneStateListener,
                    PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                )
                onDispose {
                    @Suppress("DEPRECATION")
                    telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE)
                }
            }
        }
    }

    // Lifecycle observer to request permissions when the Composable enters the Resumed state
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, permissionLauncher, permissionGranted) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                if (!permissionGranted) {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_PHONE_STATE
                        )
                    )
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    BarIndicator(signalStrengthLevel, R.drawable.signal, ltr = false)
}