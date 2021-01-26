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

        val view: View = circleLayout.selectedItem
    }

    override fun onItemSelected(view: View?) {
        return
    }

    override fun onItemClick(view: View?) {
        when (view!!.id) {
            R.id.c -> {
                soundPlayer.playSound("C3")
            }
            R.id.g -> {
                soundPlayer.playSound("G3")
            }
            R.id.d -> {
                soundPlayer.playSound("D3")
            }
            R.id.a -> {
                soundPlayer.playSound("A3")
            }
            R.id.e -> {
                soundPlayer.playSound("E3")
            }
            R.id.b -> {
                soundPlayer.playSound("B3")
            }
            R.id.gMol -> {
                soundPlayer.playSound("F#3")
            }
            R.id.dMol -> {
                soundPlayer.playSound("C#3")
            }
            R.id.aMol -> {
                soundPlayer.playSound("G#3")
            }
            R.id.eMol -> {
                soundPlayer.playSound("D#3")
            }
            R.id.bMol -> {
                soundPlayer.playSound("A#3")
            }
            R.id.f -> {
                soundPlayer.playSound("F3")
            }
        }
    }

    override fun onRotationFinished(view: View?) {
        return
    }

    override fun onCenterClick() {
        return
    }
}