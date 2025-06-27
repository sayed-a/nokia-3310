package nostalgia.devices.nokia3310.apps.menu

import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.apps.App
import nostalgia.devices.nokia3310.apps.AppRouter
import nostalgia.devices.nokia3310.shell.ArrowDirection
import nostalgia.devices.nokia3310.shell.Key

class MenuAppButtonListener(val router: AppRouter, val menuViewModel: MenuViewModel): ButtonListener {
    override fun onKeypadPressed(key: Key) {
        menuViewModel.setMenuItem(key.ordinal)
    }

    override fun onMenuPressed() {
        router.goTo(menuViewModel.currentMenuItem.value.app)
    }

    override fun onCancelPressed() {
        router.goTo(App.Home)
    }

    override fun onArrowPressed(arrow: ArrowDirection) {
        if (arrow == ArrowDirection.UP) {
            menuViewModel.previous()
        } else {
            menuViewModel.next()
        }
    }
}