package nostalgia.devices.nokia3310.apps

import androidx.compose.runtime.Composable
import nostalgia.devices.nokia3310.apps.calculator.CalculatorApp
import nostalgia.devices.nokia3310.apps.caller.CallerApp
import nostalgia.devices.nokia3310.apps.dialler.DiallerApp
import nostalgia.devices.nokia3310.apps.games.GamesApp
import nostalgia.devices.nokia3310.apps.home.HomeApp
import nostalgia.devices.nokia3310.apps.menu.MenuApp
import nostalgia.devices.nokia3310.apps.messages.MessagesApp
import nostalgia.devices.nokia3310.apps.phonebook.PhonebookApp
import nostalgia.devices.nokia3310.apps.simulator.SimulatorApp
import nostalgia.devices.nokia3310.apps.tones.TonesApp

enum class App(val composable: @Composable () -> Unit) {
    Calculator({ CalculatorApp() }),
    Caller({ CallerApp() }),
    Dialler({ DiallerApp() }),
    Games({ GamesApp() }),
    Home({ HomeApp() }),
    Menu({ MenuApp() }),
    Messages({ MessagesApp() }),
    Phonebook({ PhonebookApp() }),
    Simulator({ SimulatorApp() }),
    Tones({ TonesApp() })
}