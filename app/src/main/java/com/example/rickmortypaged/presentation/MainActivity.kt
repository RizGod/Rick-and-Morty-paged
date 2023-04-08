package com.example.rickmortypaged.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rickmortypaged.R
import com.example.rickmortypaged.presentation.character_list.CharactersFragment

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