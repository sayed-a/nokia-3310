package nostalgia.devices.nokia3310.apps.dialler

import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.apps.App
import nostalgia.devices.nokia3310.apps.AppRouter
import nostalgia.devices.nokia3310.shell.ArrowDirection
import nostalgia.devices.nokia3310.shell.Key

class DiallerButtonListener(val router: AppRouter, val diallerViewModel: DiallerViewModel): ButtonListener {
    override fun onKeypadPressed(key: Key) {
        diallerViewModel.addDigit(key.topText)
    }

    override fun onMenuPressed() {
        diallerViewModel.clear()
        router.goTo(App.Caller)
    }

    override fun onCancelPressed() {
        if (diallerViewModel.isOnLastDigit) {
            router.goTo(App.Home)
        }

        diallerViewModel.backspace()
    }

    override fun onArrowPressed(arrow: ArrowDirection) {

    }
}