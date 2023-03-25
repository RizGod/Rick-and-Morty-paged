package com.example.rickmortypaged.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickmortypaged.R
import com.example.rickmortypaged.presentation.character_list.CharactersFragment
import com.example.rickmortypaged.presentation.detailed_information.CharacterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharactersFragment.newInstance())
                .commitNow()
    }
}