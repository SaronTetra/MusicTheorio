package me.sarian.musictheorio

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.kekstudio.musictheory.Note
import kotlin.random.Random

class IntervalsActivity : AppCompatActivity() {
    private var bannerAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals)

        MobileAds.initialize(this) {}

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
        val nextButton: Button = findViewById(R.id.button_next)
        nextButton.setOnClickListener {
            val rand = Random.nextInt(1,12)
            val rand2 = Random.nextInt(1,12)
            val note = Note("C")

//           val rootNote = note.add(intervals[rand])
            val rootNote = Note(notes[rand2])

            val textInterval = findViewById<TextView>(R.id.interval)
            val textNote = findViewById<TextView>(R.id.note)
            val textNote2 = findViewById<TextView>(R.id.note2)

            textInterval.text = intervals[rand]
            // from
            textNote.text = rootNote.name.replace("b","♭︎ ").replace("#","♯︎ ")

            textNote2.text = rootNote.add(intervals[rand]).name.replace("b","♭︎ ").replace("#","♯︎ ")
        }


        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView?.loadAd(adRequest)
    }
}