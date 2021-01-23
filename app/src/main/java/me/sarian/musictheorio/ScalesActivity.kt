package me.sarian.musictheorio

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ScalesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener  {

    var type: String = "Major"
    var mode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scales)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

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

    fun setScaleName() {
        val textView = findViewById<TextView>(R.id.scale_name)
        textView.text = "${this.mode} ${this.type}"
    }

    fun onRadioButtonClicked(view: View) {
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