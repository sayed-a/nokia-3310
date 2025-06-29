package nostalgia.devices.nokia3310.apps.common.textmenu

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TextMenuViewModel @Inject constructor(): ViewModel() {

    private val isOverflowingMenu: Boolean
        get() = menuItems.size > 5

    private var onItemClicked: ((Int, String) -> Unit)? = null
    private var menuItems: List<String> = listOf()
    private var currentMenuItem: Int = 0
    private var displayStartIndex: Int = 0
    private var displayEndIndex: Int = 0
    var displayIndex: Int = 0
        private set

    private val _displayIndexFlow = MutableStateFlow(0)
    val displayIndexFlow = _displayIndexFlow.asStateFlow()

    private val _displayedItemsFlow = MutableStateFlow(listOf<String>())
    val displayedItemsFlow = _displayedItemsFlow.asStateFlow()

    fun initialise(items: List<String>, onItemClicked: (Int, String) -> Unit) {
        menuItems = items
        this.onItemClicked = onItemClicked
        currentMenuItem = 0
        displayStartIndex = 0
        displayEndIndex = minOf(5, items.size)
        displayIndex = 0

        runBlocking {
            _displayIndexFlow.emit(displayIndex)
            _displayedItemsFlow.emit(getDisplayedItems())
        }
    }

    fun nextItem() {
        // If we are on the last item in an overflowing list, stop
        if (isOverflowingMenu && displayEndIndex == menuItems.size) return

        currentMenuItem = minOf(currentMenuItem + 1, menuItems.lastIndex)

        // If we are on the last displayed item on-screen
        if (displayIndex == 4 || displayIndex == menuItems.lastIndex) {
            if (isOverflowingMenu) {
                // Move the display window down
                displayStartIndex++
                displayEndIndex = minOf(displayEndIndex + 1, menuItems.size)
            }
        } else {
            // Move the display indicator
            displayIndex++
        }

        runBlocking {
            _displayIndexFlow.emit(displayIndex)
            _displayedItemsFlow.emit(getDisplayedItems())
        }
    }

    fun previousItem() {
        // If we are on the first item in an overflowing list, stop
        if (isOverflowingMenu && displayStartIndex == 0 && displayIndex == 0) return

        currentMenuItem = maxOf(currentMenuItem - 1, 0)

        // If we are on the first displayed item on-screen
        if (displayIndex == 0) {
            if (isOverflowingMenu) {
                // Move the display window up
                displayStartIndex = maxOf(displayStartIndex - 1, 0)
                displayEndIndex = minOf(displayEndIndex - 1, menuItems.size)
            }
        } else {
            // Move the display indicator
            displayIndex--
        }

        runBlocking {
            _displayIndexFlow.emit(displayIndex)
            _displayedItemsFlow.emit(getDisplayedItems())
        }
    }

    fun getDisplayedItems(): List<String> {
        return menuItems.subList(displayStartIndex, displayEndIndex)
    }

    fun selectItem() {
        this.onItemClicked?.invoke(currentMenuItem, menuItems[currentMenuItem])
    }
}