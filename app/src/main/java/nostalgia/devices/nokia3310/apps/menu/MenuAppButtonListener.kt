package nostalgia.devices.nokia3310.apps.menu

import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.apps.App
import nostalgia.devices.nokia3310.apps.AppRouter
import nostalgia.devices.nokia3310.shell.ArrowDirection
import nostalgia.devices.nokia3310.shell.Key

class MenuAppButtonListener(val router: AppRouter): ButtonListener {
    override fun onKeypadPressed(key: Key) {
        /* NOOP */
    }

    override fun onMenuPressed() {
        /* NOOP */
    }

    override fun onCancelPressed() {
        router.goTo(App.Home)
    }

    override fun onArrowPressed(arrow: ArrowDirection) {
        // TODO: Change current menu item
    }
}