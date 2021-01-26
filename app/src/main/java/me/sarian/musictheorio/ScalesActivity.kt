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
import kotlin.random.Random


class ScalesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    private lateinit var bannerAdView: AdView

    private var type: String = "Major"
    private var mode: String = ""
    private lateinit var soundPlayer: SoundPlayer

    private var randomNote: Int = 0

    private val majorScale = intArrayOf(2,2,1,2,2,2,1)
    private val minorScale = intArrayOf(2,1,2,2,1,2,2)

    private val harmonicMinorScale = intArrayOf(2,1,2,2,1,3,1)
    private val melodicMinorScale = intArrayOf(2,1,2,2,2,2,1)

    private val harmonicMajorScale = intArrayOf(2,2,1,2,1,3,1)
    private val melodicMajorScale = intArrayOf(2,2,1,2,2,2,1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scales)

        MobileAds.initialize(this) {}

        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView.loadAd(adRequest)

        soundPlayer = SoundPlayer(assets)

        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this

        val radioGroup = findViewById<RadioGroup>(R.id.radioScaleType)
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val checkedRadioButton = group.findViewById<View>(checkedId) as RadioButton
            val isChecked = checkedRadioButton.isChecked
            if (isChecked) {
                this.type = checkedRadioButton.text.toString()
                setScaleName()
            }
        }

        val noteView: TextView = findViewById(R.id.note_name)
        val nextButton: Button = findViewById(R.id.button_next)
        nextButton.setOnClickListener {
            val rootNote = Random.nextInt(1, 12)
            this.randomNote = rootNote
            noteView.text = NotesIndexMap.indexNote[rootNote]!!.dropLast(1)

        }

        noteView.setOnClickListener{
            playScale(randomNote, 0, selectScale(type, mode), true)
        }

        nextButton.performClick()
    }

    private fun selectScale(type: String, mode: String): IntArray {
        if(type == "Major") {
            return when (mode) {
                "Natural" -> majorScale
                "Melodic" -> melodicMajorScale
                "Harmonic" -> harmonicMajorScale
                else -> majorScale
            }
        }
        else {
            return when (mode) {
                "Natural" -> minorScale
                "Melodic" -> melodicMinorScale
                "Harmonic" -> harmonicMinorScale
                else -> minorScale
            }
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
        soundPlayer.playSound(NotesIndexMap.indexNote[note].toString())
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