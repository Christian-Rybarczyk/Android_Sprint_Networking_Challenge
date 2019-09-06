package com.rybarstudios.pokemonsprint.model

data class Pokemon(val name: String,
                   val abilities: MutableList<String>,
                   val sprites: MutableList<String>,
                   val types: MutableList<String>)