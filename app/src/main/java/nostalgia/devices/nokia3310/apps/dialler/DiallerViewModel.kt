package nostalgia.devices.nokia3310.apps.dialler

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DiallerViewModel @Inject constructor() : ViewModel() {
    private val _numberFlow: MutableStateFlow<String> = MutableStateFlow("")
    val numberFlow: StateFlow<String> = _numberFlow.asStateFlow()

    val isOnLastDigit: Boolean
        get() = numberFlow.value.length == 1

    fun addDigit(digit: String) {
        runBlocking { _numberFlow.emit(_numberFlow.value + digit) }
    }

    fun backspace() {
        runBlocking { _numberFlow.emit(_numberFlow.value.dropLast(1)) }
    }

    fun clear() {
        runBlocking { _numberFlow.emit("") }
    }
}