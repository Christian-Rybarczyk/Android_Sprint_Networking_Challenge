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
import androidx.recyclerview.widget.RecyclerView
import com.rybarstudios.pokemonsprint.R
import com.rybarstudios.pokemonsprint.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_list_display_item.view.*

class PokemonListAdapter(val pokemonList: MutableList<Pokemon>) : RecyclerView.Adapter<PokemonListAdapter.CustomViewHolder>() {

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
        val data = pokemonList[position]
        holder.pokemonName.text = data.name

        holder.layout.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("pokemon", data)
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
            true
        }
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val layout: LinearLayout = view.pokemon_list_layout
        val pokemonName: TextView = view.textView_pokemon_list_name
    }
}