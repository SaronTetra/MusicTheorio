package me.sarian.musictheorio

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

// Remove the line below after defining your own ad unit ID.
private const val TOAST_TEXT = "Test ads are being shown. " +
        "To show live ads, replace the ad unit ID in res/values/strings.xml " +
        "with your own ad unit ID."

class MainActivity : AppCompatActivity() {
    private lateinit var soundPlayer: SoundPlayer

    private lateinit var interstitialAd: InterstitialAd

    private lateinit var scalesButton: Button
    private lateinit var intervalsButton: Button
    private lateinit var circleOfFifthsButton: Button
    private lateinit var playButton: Button

    private var soundIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPlayer = SoundPlayer(assets)

        // Create the next level button, which tries to show an interstitial when clicked.
        scalesButton = findViewById(R.id.scales_button)
        scalesButton.setOnClickListener {
            interstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    goToScales()
                }
            }
            showInterstitial()
        }

        intervalsButton = findViewById(R.id.intervals_button)
        intervalsButton.setOnClickListener {
            interstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    goToIntervals()
                }
            }
            showInterstitial()
        }

        circleOfFifthsButton = findViewById(R.id.circle_of_fifths_button)
        circleOfFifthsButton.setOnClickListener {
            interstitialAd.adListener = object : AdListener() {
                override fun onAdClosed() {
                    goToCircleOfFifths()
                }
            }
            showInterstitial()
        }

        playButton = findViewById(R.id.play_sound)
        playButton.setOnClickListener {
            soundIndex++
            if (soundIndex == 25) soundIndex = 1
            soundPlayer.playSound(NotesIndexMap.indexNote[soundIndex]!!)
        }



        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        interstitialAd = newInterstitialAd()
        loadInterstitial()

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }

    private fun newInterstitialAd(): InterstitialAd {
        return InterstitialAd(this).apply {
            adUnitId = getString(R.string.interstitial_ad_unit_id)
        }
    }

    private fun showInterstitial() {
        // Show the ad if it"s ready. Otherwise toast and reload the ad.
        if (interstitialAd.isLoaded) {
            interstitialAd.show()
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadInterstitial() {
        scalesButton.isEnabled = false
        intervalsButton.isEnabled = false
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        interstitialAd.loadAd(adRequest)
        scalesButton.isEnabled = true
        intervalsButton.isEnabled = true
    }

    private fun goToScales() {
        interstitialAd = newInterstitialAd()
        loadInterstitial()
        val intent = Intent(this, ScalesActivity::class.java)
        startActivity(intent)
    }

    private fun goToIntervals() {
        interstitialAd = newInterstitialAd()
        loadInterstitial()
        val intent = Intent(this, IntervalsActivity::class.java)
        startActivity(intent)
    }

    private fun goToCircleOfFifths() {
        interstitialAd = newInterstitialAd()
        loadInterstitial()
        val intent = Intent(this, CircleOfFifthsActivity::class.java)
        startActivity(intent)
    }
}