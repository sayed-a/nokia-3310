package nostalgia.devices.nokia3310.apps.menu

import androidx.annotation.DrawableRes
import nostalgia.devices.nokia3310.R
import nostalgia.devices.nokia3310.apps.App

enum class MenuItem(val text: String, @DrawableRes val pictogramResId: Int, val app: App) {
    PHONEBOOK("Phonebook", R.drawable.phonebook, App.Phonebook),
    MESSAGES("Messages", R.drawable.messages, App.Messages),
    TONES("Tones", R.drawable.tones, App.Tones),
    GAMES("Games", R.drawable.games, App.Games),
    CALCULATOR("Calculator", R.drawable.calculator, App.Calculator),
    SIMULATOR("Simulate", R.drawable.simulator, App.Simulator)
}