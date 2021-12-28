package com.alejandroramirez.pikapika.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alejandroramirez.pikapika.domain.model.Pokemon

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    navigateToPokemonDetail: (String) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(pokemons, key = { it.id }) { pokemon ->
            PokemonListItem(pokemon = pokemon, onClick = navigateToPokemonDetail)
        }
    }
}

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    onClick: (String) -> Unit
) {

}