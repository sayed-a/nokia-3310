package nostalgia.devices.nokia3310.apps.home

import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.apps.App
import nostalgia.devices.nokia3310.apps.AppRouter
import nostalgia.devices.nokia3310.apps.dialler.DiallerViewModel
import nostalgia.devices.nokia3310.shell.ArrowDirection
import nostalgia.devices.nokia3310.shell.Key

class HomeAppButtonListener(val router: AppRouter, val diallerViewModel: DiallerViewModel): ButtonListener {

    override fun onKeypadPressed(key: Key) {
        diallerViewModel.addDigit(key.topText)
        router.goTo(App.Dialler)
    }

    override fun onMenuPressed() {
        router.goTo(App.Menu)
    }

    override fun onCancelPressed() {
        /* NOOP */
    }

    override fun onArrowPressed(arrow: ArrowDirection) {
        /* NOOP */
    }
}