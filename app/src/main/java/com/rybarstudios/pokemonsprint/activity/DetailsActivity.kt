package com.rybarstudios.pokemonsprint.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rybarstudios.pokemonsprint.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val data = intent.getSerializableExtra("pokemon")
    }
}
