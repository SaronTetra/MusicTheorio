package me.sarian.musictheorio

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*
import kotlin.random.Random

class IntervalsActivity : AppCompatActivity() {
    private var bannerAdView: AdView? = null
    private lateinit var soundPlayer: SoundPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals)

        MobileAds.initialize(this) {}
        soundPlayer = SoundPlayer(assets)

        val intervals = mapOf(
                1 to "m2",
                2 to "M2",
                3 to "m3",
                4 to "M3",
                5 to "P4",
                6 to "A4",
                7 to "P5",
                8 to "m6",
                9 to "M6",
                10 to "m7",
                11 to "M7",
                12 to "P8"
        )
        val notes = mapOf(
                1 to "C",
                2 to "C#",
                3 to "D",
                4 to "D#",
                5 to "E",
                6 to "F",
                7 to "F#",
                8 to "G",
                9 to "G#",
                10 to "A",
                11 to "A#",
                12 to "B"
        )
        val betterNotes = mapOf(
                1 to "C3",
                2 to "C#3",
                3 to "D3",
                4 to "D#3",
                5 to "E3",
                6 to "F3",
                7 to "F#3",
                8 to "G3",
                9 to "G#3",
                10 to "A3",
                11 to "A#3",
                12 to "B3",
                13 to "C4",
                14 to "C#4",
                15 to "D4",
                16 to "D#4",
                17 to "E4",
                18 to "F4",
                19 to "F#4",
                20 to "G4",
                21 to "G#4",
                22 to "A4",
                23 to "A#4",
                24 to "B4"
        )

        val nextButton: Button = findViewById(R.id.button_next)
        nextButton.setOnClickListener {
            val randInterval = Random.nextInt(1, 12)
            val randRoot = Random.nextInt(1, 12)

//           val rootNote = note.add(intervals[rand])
//            val rootNote = Note(notes[rand2])
            val rootNote = notes[randRoot]

            val textInterval = findViewById<TextView>(R.id.interval)
            val textNote = findViewById<TextView>(R.id.note)
            val textNote2 = findViewById<TextView>(R.id.note2)

            textInterval.text = intervals[randInterval]
            // from
//            textNote.text = rootNote.name.replace("b", "♭︎ ").replace("#", "♯︎ ")
//            soundPlayer.playSound("${rootNote.name}3")
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    soundPlayer.playSound(betterNotes[randRoot + randInterval].toString())
                }
            }, 200)
            textNote.text = betterNotes[randRoot]!!.dropLast(1)
            textNote2.text = betterNotes[randRoot + randInterval]!!.dropLast(1)
            soundPlayer.playSound(betterNotes[randRoot].toString())
//            textNote2.text = rootNote.add(intervals[rand]).name.replace("b", "♭︎ ").replace("#", "♯︎ ")
        }


        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView?.loadAd(adRequest)
    }
}