package me.sarian.musictheorio

import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class ScalesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {
    private var bannerAdView: AdView? = null

    private var type: String = "Major"
    private var mode: String = ""
    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scales)

        MobileAds.initialize(this) {}

        bannerAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        bannerAdView?.loadAd(adRequest)

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
}