package nostalgia.devices.nokia3310.apps.common

import android.content.Context
import android.media.MediaPlayer

class RingtoneManager {

    val currentRingtone: Ringtone
    var isPlaying: Boolean = false
        private set
    private var mediaPlayer: MediaPlayer? = null

    constructor() {
        // TODO: Get this from shared prefs
        currentRingtone = Ringtone.NOKIA_TUNE
    }

    fun playRingtone(context: Context) {
        isPlaying = true
        mediaPlayer = MediaPlayer.create(context, currentRingtone.audioRes)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()
    }

    fun stop() {
        isPlaying = false
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}