package me.sarian.musictheorio

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*


class ScalesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    private var bannerAdView: AdView? = null

    private var type: String = "Major"
    private var mode: String = ""
    private lateinit var soundPlayer: SoundPlayer

    private val betterNotes = mapOf(
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
    private val majorScale = intArrayOf(2,2,1,2,2,2,1)
    private val minorScale = intArrayOf(2,1,2,2,1,2,2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scales)

        MobileAds.initialize(this) {}

        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView?.loadAd(adRequest)

        soundPlayer = SoundPlayer(assets)

        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this

        val radioGroup = findViewById<RadioGroup>(R.id.radioScaleType)
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            val checkedRadioButton = group.findViewById<View>(checkedId) as RadioButton
            val isChecked = checkedRadioButton.isChecked
            if (isChecked) {
                this.type = checkedRadioButton.text.toString()
                setScaleName()
            }
        })

        val nextButton: Button = findViewById(R.id.button_next)
        nextButton.setOnClickListener {
            playScale(1, 0, majorScale, true)

        }
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val temp = parent.getItemAtPosition(pos)
        this.mode = temp.toString()
        setScaleName()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun setScaleName() {
        val textView = findViewById<TextView>(R.id.scale_name)
        textView.text = "${this.mode} ${this.type}"
    }

    private fun playScale(note:Int, index: Int, scale: IntArray, direction: Boolean) {
        soundPlayer.playSound(betterNotes[note].toString())
        if(direction) {
            if (index < scale.size) {
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        playScale(note + scale[index], index + 1, scale,  true)
                    }
                }, 400)
            }
            else {
                playScale(note, index - 1, scale,  false)
            }
        } else {
            if (index >= 0) {
                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        playScale(note - scale[index], index - 1, scale, false)
                    }
                }, 400)
            }
        }
    }

}