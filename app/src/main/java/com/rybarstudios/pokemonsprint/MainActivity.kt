package com.rybarstudios.pokemonsprint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rybarstudios.pokemonsprint.adapter.PokemonListAdapter
import com.rybarstudios.pokemonsprint.model.Pokemon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val pokemonList = mutableListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_pokemon_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PokemonListAdapter(pokemonList)
        }
    }
}
