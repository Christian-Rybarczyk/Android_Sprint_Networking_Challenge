package com.rybarstudios.pokemonsprint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rybarstudios.pokemonsprint.activity.DetailsActivity
import com.rybarstudios.pokemonsprint.adapter.PokemonListAdapter
import com.rybarstudios.pokemonsprint.model.Pokemon
import com.rybarstudios.pokemonsprint.model.SerializedPokemon
import com.rybarstudios.pokemonsprint.retrofit.PokemonAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<Pokemon> {
    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
        Log.e("TAG", t.toString())
        Toast.makeText(this, "Call failed", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
        if (response.isSuccessful) {
            val pokemon = response.body() as Pokemon

            val ability = mutableListOf<String>()
            pokemon.abilities.forEach {
                ability.add(it.ability.name)
            }

            val types = mutableListOf<String>()
            pokemon.types.forEach {
                types.add(it.type.name)
            }

            val serializedPokemon = SerializedPokemon(pokemon.name,
                pokemon.sprites.front_default,
                pokemon.id,
                ability,
                types
                )

            pokemonList.add(serializedPokemon)
            recyclerview_pokemon_list.adapter?.notifyDataSetChanged()

            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(POKEMON_KEY, serializedPokemon)
            startActivity(intent)
        }
    }

    val pokemonList = mutableListOf<SerializedPokemon>()
    lateinit var pokemonSearch: PokemonAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonSearch = PokemonAPI.create()

        recyclerview_pokemon_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PokemonListAdapter(pokemonList)
        }

        imageButton_search.setOnClickListener {
            val searchText = editText_search.text.toString()
            pokemonSearch(searchText)
        }
    }

    private fun pokemonSearch(id: String) {
        pokemonSearch.getPokemonById(id).enqueue(this)
    }

    companion object {
        const val POKEMON_KEY = "serializedPokemon"
    }

}
