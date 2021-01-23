package me.sarian.musictheorio

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import java.util.*


// Remove the line below after defining your own ad unit ID.
private const val TOAST_TEXT = "Test ads are being shown. " +
        "To show live ads, replace the ad unit ID in res/values/strings.xml " +
        "with your own ad unit ID."
private const val START_LEVEL = 1

class MainActivity : AppCompatActivity() {

    private var currentLevel: Int = 0
    private var interstitialAd: InterstitialAd? = null
    private lateinit var nextLevelButton: Button
    private lateinit var levelTextView: TextView

    private var soundIndex: Int = 0

    private val soundMap = mutableMapOf<String, Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        val sp: SoundPool = SoundPool.Builder().setAudioAttributes(attributes).setMaxStreams(10).build()
        val sm = ArrayList<Int>()
        assets.list("piano")?.forEachIndexed() { index, it->
            val afd = assets.openFd("piano/$it")
            println(it)
            soundMap[it.removeSuffix(".wav")] = index
            sm.add(
                sp.load(afd, 1)
            )
        }

        // Create the next level button, which tries to show an interstitial when clicked.
        nextLevelButton = findViewById(R.id.next_level_button)
        nextLevelButton.isEnabled = false
        nextLevelButton.setOnClickListener {
            showInterstitial()
            playSound(sp, sm, soundIndex)
            this.soundIndex++
            this.soundIndex = this.soundIndex.rem(24)
        }

        levelTextView = findViewById(R.id.level)
        // Create the text view to show the level number.
        currentLevel = START_LEVEL

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        interstitialAd = newInterstitialAd()
        loadInterstitial()

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()


    }

    fun sendMessage(view: View){
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()
        val intent = Intent(this, ScalesActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    private fun playSound(sp: SoundPool, sm: ArrayList<Int>, index:Int) {

        sp.play(sm[index], 1F, 1F, 1, 0, 1f)
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
            adListener = object : AdListener() {
                override fun onAdLoaded() {
                    nextLevelButton.isEnabled = true
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    nextLevelButton.isEnabled = true
                }

                override fun onAdClosed() {
                    // Proceed to the next level.
                    goToNextLevel()
                }
            }
        }
    }

    private fun showInterstitial() {
        // Show the ad if it"s ready. Otherwise toast and reload the ad.
        if (interstitialAd?.isLoaded == true) {
            interstitialAd?.show()
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show()
            goToNextLevel()
        }
    }

    private fun loadInterstitial() {
        // Disable the next level button and load the ad.
        nextLevelButton.isEnabled = false
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .build()
        interstitialAd?.loadAd(adRequest)
    }

    private fun goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        levelTextView.text = "Level " + (++currentLevel)
        interstitialAd = newInterstitialAd()
        loadInterstitial()
    }
}