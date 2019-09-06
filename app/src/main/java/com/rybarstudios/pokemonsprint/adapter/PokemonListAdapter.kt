package com.rybarstudios.pokemonsprint.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.rybarstudios.pokemonsprint.MainActivity
import com.rybarstudios.pokemonsprint.R
import com.rybarstudios.pokemonsprint.activity.DetailsActivity
import com.rybarstudios.pokemonsprint.model.Pokemon
import com.rybarstudios.pokemonsprint.model.SerializedPokemon
import kotlinx.android.synthetic.main.pokemon_list_display_item.view.*
import java.io.Serializable

class PokemonListAdapter(val pokemonList: MutableList<SerializedPokemon>) : RecyclerView.Adapter<PokemonListAdapter.CustomViewHolder>() {

    var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.pokemon_list_display_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.pokemonName.text = pokemonList[position].name

        holder.layout.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(MainActivity.POKEMON_KEY, pokemonList[position])
            (context as Activity).startActivity(intent)
        }

        holder.layout.setOnLongClickListener {
            val title = "Delete Pokemon"
            val message = "Are you sure you want to delete this Pokemon?"
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("YES") { dialog, which ->
                    pokemonList.removeAt(position)
                    notifyDataSetChanged()
                }
                .setNegativeButton("NO") { _, _ -> }
                .create()
                .show()
            notifyDataSetChanged()
            true
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: LinearLayout = view.pokemon_list_layout
        val pokemonName: TextView = view.textView_pokemon_list_name
    }
}