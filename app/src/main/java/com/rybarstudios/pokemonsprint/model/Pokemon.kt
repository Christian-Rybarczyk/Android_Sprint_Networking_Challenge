package com.rybarstudios.pokemonsprint.model

import java.io.Serializable

data class Pokemon(val name: String,
                   val abilities: MutableList<String>,
                   val sprites: MutableList<String>,
                   val types: MutableList<String>)
    :Serializable