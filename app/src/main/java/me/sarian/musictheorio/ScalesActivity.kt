package me.sarian.musictheorio

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ScalesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scales)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val spinner: Spinner = findViewById(R.id.spinner)
        spinner.onItemSelectedListener = this


        val textView = findViewById<TextView>(R.id.scale_name).apply {
            text = message
        }
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val temp = parent.getItemAtPosition(pos)
        Toast.makeText(this, temp.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}