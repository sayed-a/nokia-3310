package nostalgia.devices.nokia3310

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import nostalgia.devices.nokia3310.apps.App
import nostalgia.devices.nokia3310.apps.AppRouter
import nostalgia.devices.nokia3310.apps.caller.CallerButtonListener
import nostalgia.devices.nokia3310.apps.dialler.DiallerButtonListener
import nostalgia.devices.nokia3310.apps.dialler.DiallerViewModel
import nostalgia.devices.nokia3310.apps.home.HomeAppButtonListener
import nostalgia.devices.nokia3310.apps.menu.MenuAppButtonListener
import nostalgia.devices.nokia3310.shell.ArrowDirection
import nostalgia.devices.nokia3310.shell.Key
import nostalgia.devices.nokia3310.shell.Phone
import nostalgia.devices.nokia3310.ui.theme.Nokia3310Theme

@AndroidEntryPoint
class PhoneActivity : ComponentActivity(), ButtonListener {
    val diallerViewModel: DiallerViewModel by viewModels()

    val router = AppRouter()
    val buttonListeners: Map<App, ButtonListener> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (buttonListeners as MutableMap).apply {
            put(App.Caller, CallerButtonListener(router))
            put(App.Dialler, DiallerButtonListener(router, diallerViewModel))
            put(App.Home, HomeAppButtonListener(router, diallerViewModel))
            put(App.Menu, MenuAppButtonListener(router))
        }

        enableEdgeToEdge()
        setContent {
            Nokia3310Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    Phone(this, router)
                }
            }
        }
    }

    //region Button Listener Implementation
    override fun onKeypadPressed(key: Key) {
        buttonListeners[router.currentAppFlow.value]?.onKeypadPressed(key)
    }

    override fun onMenuPressed() {
        buttonListeners[router.currentAppFlow.value]?.onMenuPressed()
    }

    override fun onCancelPressed() {
        buttonListeners[router.currentAppFlow.value]?.onCancelPressed()
    }

    override fun onArrowPressed(arrow: ArrowDirection) {
        buttonListeners[router.currentAppFlow.value]?.onArrowPressed(arrow)
    }
    //endregion
}