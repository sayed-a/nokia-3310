package nostalgia.devices.nokia3310.apps.common.textmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nostalgia.devices.nokia3310.shell.ArrowDirection
import nostalgia.devices.nokia3310.ui.theme.ScreenColour

@Composable
fun TextMenu(textMenuViewModel: TextMenuViewModel = hiltViewModel(), modifier: Modifier = Modifier) {
    val displayedItems = textMenuViewModel.displayedItemsFlow.collectAsStateWithLifecycle()
    val displayIndex = textMenuViewModel.displayIndexFlow.collectAsStateWithLifecycle()

    LazyColumn(modifier.fillMaxWidth()) {
        items(displayedItems.value.size) {
            val backgroundColour = if (it == displayIndex.value) Black else ScreenColour
            val textColour = if (it == displayIndex.value) ScreenColour else Black

            Box(Modifier.fillMaxWidth().height(30.dp).background(backgroundColour)) {
                Text(displayedItems.value[it], color = textColour)
            }
        }
    }
}

private fun getDisplayedItems(activeItem: Int, direction: ArrowDirection, displayedWindow: IntRange, items: List<String>): Triple<Int, IntRange, List<String>> {
    when (direction) {
        ArrowDirection.UP -> {
            throw NotImplementedError()
        }
        ArrowDirection.DOWN -> {
            return when {
                activeItem < 4 -> {
                    Triple(activeItem + 1, displayedWindow, items.subList(displayedWindow.first, displayedWindow.last))
                }

                else -> {
                    Triple(minOf(4, items.size), (displayedWindow.first + 1..displayedWindow.last + 1), items.subList(displayedWindow.first + 1, displayedWindow.last + 1))
                }
            }
        }

        else -> {throw IllegalArgumentException("Arrow direction cannot be UNKNOWN")
        }
    }
    return when {
        items.size <= 5 -> Triple(activeItem, 0..5, items)
        activeItem in items.lastIndex - 5 until items.size  -> Triple(activeItem, 0..5, items)
        activeItem in 0 until 5 -> Triple(activeItem, 0..5, items)
        else -> Triple(activeItem, 0..5, items)
    }
}