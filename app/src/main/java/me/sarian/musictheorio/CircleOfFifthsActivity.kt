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
    private lateinit var bannerAdView: AdView

    private lateinit var circleLayout: CircleLayout
    private lateinit var selectedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cirlce_of_fifths)

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
        return
    }

    override fun onRotationFinished(view: View?) {
        return
    }

    override fun onCenterClick() {
        return
    }
}