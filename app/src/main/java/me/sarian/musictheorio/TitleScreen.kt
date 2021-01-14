package me.sarian.musictheorio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController

class TitleScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_title_screen, container, false)

        view.findViewById<Button>(R.id.play_btn).setOnClickListener {
            findNavController(view).navigate(R.id.action_title_screen_to_register)
        }

        return view
    }
}