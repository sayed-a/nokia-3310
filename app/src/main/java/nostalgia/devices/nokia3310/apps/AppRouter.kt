package nostalgia.devices.nokia3310.apps

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

class AppRouter {
    private val _currentAppStateFlow = MutableStateFlow<App>(App.Home)
    val currentAppFlow: StateFlow<App> = _currentAppStateFlow.asStateFlow()

    fun goTo(app: App) {
        runBlocking { _currentAppStateFlow.emit(app) }
    }
}