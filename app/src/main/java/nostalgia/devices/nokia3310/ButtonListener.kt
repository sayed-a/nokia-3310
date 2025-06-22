package nostalgia.devices.nokia3310

import nostalgia.devices.nokia3310.shell.ArrowDirection
import nostalgia.devices.nokia3310.shell.Key

interface ButtonListener {

    fun onKeypadPressed(key: Key)
    fun onMenuPressed()
    fun onCancelPressed()
    fun onArrowPressed(arrow: ArrowDirection)
}