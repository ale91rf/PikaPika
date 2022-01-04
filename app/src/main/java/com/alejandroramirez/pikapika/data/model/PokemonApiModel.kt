package com.alejandroramirez.pikapika.data.model

import com.google.gson.annotations.SerializedName


data class PokemonApiModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val urlDetail: String
)