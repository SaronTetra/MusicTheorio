package me.sarian.musictheorio

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.szugyi.circlemenu.view.CircleLayout


class CircleOfFifthsActivity : AppCompatActivity(), CircleLayout.OnItemSelectedListener,
    CircleLayout.OnItemClickListener, CircleLayout.OnRotationFinishedListener,
    CircleLayout.OnCenterClickListener {
    private lateinit var soundPlayer: SoundPlayer

    private lateinit var bannerAdView: AdView

    private lateinit var circleLayout: CircleLayout
    private lateinit var selectedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cirlce_of_fifths)

        soundPlayer = SoundPlayer(assets)

        MobileAds.initialize(this) {}

        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView.loadAd(adRequest)

        circleLayout = findViewById(R.id.circle_layout)
        circleLayout.setOnItemSelectedListener(this)
        circleLayout.setOnItemClickListener(this)
        circleLayout.setOnRotationFinishedListener(this)
        circleLayout.setOnCenterClickListener(this)

        selectedTextView = findViewById(R.id.selected_textView)
    }

    override fun onItemSelected(view: View?) {
        return
    }

    override fun onItemClick(view: View?) {
        when (view!!.id) {
            R.id.c -> {
                 playChord("C3")
            }
            R.id.g -> {
                 playChord("G3")
            }
            R.id.d -> {
                 playChord("D3")
            }
            R.id.a -> {
                 playChord("A3")
            }
            R.id.e -> {
                 playChord("E3")
            }
            R.id.b -> {
                 playChord("B3")
            }
            R.id.gMol -> {
                 playChord("F#3")
            }
            R.id.dMol -> {
                 playChord("C#3")
            }
            R.id.aMol -> {
                 playChord("G#3")
            }
            R.id.eMol -> {
                 playChord("D#3")
            }
            R.id.bMol -> {
                 playChord("A#3")
            }
            R.id.f -> {
                 playChord("F3")
            }
        }
    }

    private fun playChord(note: String) {
        val index = NotesIndexMap.noteIndex[note]
        val index2 = index?.plus(4)
        val index3 = index?.plus(7)

        soundPlayer.playSound(index!!)
        soundPlayer.playSound(index2!!)
        soundPlayer.playSound(index3!!)
    }

    override fun onRotationFinished(view: View?) {
        return
    }

    override fun onCenterClick() {
        return
    }
}