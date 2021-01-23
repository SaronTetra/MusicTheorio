package me.sarian.musictheorio

import android.content.res.AssetManager
import android.media.AudioAttributes
import android.media.SoundPool

class SoundPlayer(assetManager: AssetManager) {
    companion object {
        const val assetsDirectory = "piano"
        const val fileExtension = ".wav"
        const val leftVolume = 1F
        const val rightVolume = 1F
        const val priority = 1
        const val loop = 0
        const val rate = 1f
    }

    private val noteIndexMap = mutableMapOf<String, Int>()
    private val attributes: AudioAttributes = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()
    private val soundPool: SoundPool = SoundPool.Builder()
        .setAudioAttributes(attributes)
        .setMaxStreams(10)
        .build()

    fun playSound(note: String) {
        soundPool.play(noteIndexMap[note]!!, leftVolume, rightVolume, priority, loop, rate)
    }

    fun playSound(index: Int) {
        soundPool.play(index, leftVolume, rightVolume, priority, loop, rate)
    }

    init {
        val assetsList = assetManager.list(assetsDirectory)
        assetsList!!.forEach {
            val sound = assetManager.openFd("$assetsDirectory/$it")
            val soundIndex = soundPool.load(sound, priority)
            noteIndexMap[it.removeSuffix(fileExtension)] = soundIndex
        }
    }
}