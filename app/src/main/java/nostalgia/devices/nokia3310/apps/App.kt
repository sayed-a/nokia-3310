package nostalgia.devices.nokia3310.apps

import androidx.compose.runtime.Composable
import nostalgia.devices.nokia3310.apps.caller.CallerApp
import nostalgia.devices.nokia3310.apps.dialler.DiallerApp
import nostalgia.devices.nokia3310.apps.home.HomeApp
import nostalgia.devices.nokia3310.apps.menu.MenuApp

enum class App(val composable: @Composable () -> Unit) {
    Caller({ CallerApp() }),
    Dialler({ DiallerApp() }),
    Home({ HomeApp() }),
    Menu({ MenuApp() })
}