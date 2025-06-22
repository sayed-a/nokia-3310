package nostalgia.devices.nokia3310.shell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nostalgia.devices.nokia3310.ButtonListener
import nostalgia.devices.nokia3310.apps.AppRouter
import nostalgia.devices.nokia3310.ui.theme.PhoneColour

@Composable
fun Phone(buttonListener: ButtonListener, appRouter: AppRouter, modifier: Modifier = Modifier) {
    val currentApp = appRouter.currentAppFlow.collectAsStateWithLifecycle()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(PhoneColour)
            .padding(vertical = 10.dp)
    ) {
        Earpiece(Modifier.padding(top = 20.dp))
        Screen(currentApp.value.composable, isBacklit = true, Modifier.padding(top = 20.dp))
        ButtonArea(buttonListener)
    }
}