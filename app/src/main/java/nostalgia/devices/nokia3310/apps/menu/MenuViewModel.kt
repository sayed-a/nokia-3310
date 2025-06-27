package nostalgia.devices.nokia3310.apps.menu

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(): ViewModel() {

    private val menuItems = MenuItem.entries.toList()

    private val _currentMenuItem = MutableStateFlow(menuItems.first())
    val currentMenuItem: StateFlow<MenuItem> = _currentMenuItem.asStateFlow()

    fun next() {
        val nextIndex = (menuItems.indexOf(_currentMenuItem.value) + 1) % menuItems.size
        runBlocking { _currentMenuItem.emit(menuItems[nextIndex]) }
    }

    fun previous() {
        val previousIndex = (menuItems.indexOf(_currentMenuItem.value) - 1 + menuItems.size) % menuItems.size
        runBlocking { _currentMenuItem.emit(menuItems[previousIndex]) }
    }

    fun setMenuItem(index: Int) {
        if (index >= 0 && index < menuItems.size) {
            runBlocking { _currentMenuItem.emit(menuItems[index]) }
        }
    }
}