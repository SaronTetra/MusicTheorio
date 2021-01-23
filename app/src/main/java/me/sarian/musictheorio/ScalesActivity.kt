package me.sarian.musictheorio

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class ScalesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    private var bannerAdView: AdView? = null

    private var type: String = "Major"
    private var mode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scales)

        MobileAds.initialize(this) {}

        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView?.loadAd(adRequest)

        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this


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

    private fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            when (view.getId()) {
                R.id.radioMajor ->
                    if (checked) {
                        this.type = "Major"
                    }
                R.id.radioMinor ->
                    if (checked) {
                        this.type = "Minor"
                    }
            }
            setScaleName()
        }
    }
}