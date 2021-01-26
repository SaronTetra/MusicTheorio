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
    private lateinit var bannerAdView: AdView
    private lateinit var soundPlayer: SoundPlayer
    private var randomInterval: Int = 0
    private var randomNote: Int = 0

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

        val nextButton: Button = findViewById(R.id.button_next)
        nextButton.setOnClickListener {
            val randInterval = Random.nextInt(1, 12)
            val randRoot = Random.nextInt(1, 12)
            this.randomInterval = randInterval
            this.randomNote = randRoot

            val textInterval = findViewById<TextView>(R.id.interval)
            val textNote = findViewById<TextView>(R.id.note)
            val textNote2 = findViewById<TextView>(R.id.note2)

            textInterval.text = intervals[randInterval]
            playInterval(NotesIndexMap.indexNote, randRoot, randInterval)
            textNote.text = NotesIndexMap.indexNote[randRoot]!!.dropLast(1)
            textNote2.text = NotesIndexMap.indexNote[randRoot + randInterval]!!.dropLast(1)
        }

        val noteView: TextView = findViewById(R.id.note)
        noteView.setOnClickListener{
            playInterval(NotesIndexMap.indexNote, this.randomNote, this.randomInterval)
        }

        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView.loadAd(adRequest)

        nextButton.performClick()
    }

    private fun playInterval(betterNotes: Map<Int, String>, randRoot: Int, randInterval: Int) {
        soundPlayer.playSound(betterNotes[randRoot].toString())
        Timer().schedule(object : TimerTask() {
            override fun run() {
                soundPlayer.playSound(betterNotes[randRoot + randInterval].toString())
            }
        }, 200)
    }
}