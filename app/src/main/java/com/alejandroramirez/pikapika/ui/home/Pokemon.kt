package com.alejandroramirez.pikapika.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alejandroramirez.pikapika.domain.model.Pokemon
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.alejandroramirez.pikapika.R

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    navigateToPokemonDetail: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(pokemons, key = { it.id }) { pokemon ->
            PokemonListItem(pokemon = pokemon, onClick = navigateToPokemonDetail)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    onClick: (Int) -> Unit
) {
    Card(
        onClick = { onClick(pokemon.id) },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        var expanded by remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = pokemon.imageUrl,
                        builder = {
                            crossfade(true)
                            size(OriginalSize)
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded) {
                    Text(
                        text = stringResource(R.string.weight, pokemon.weight.toString())
                    )
                    Text(
                        text = stringResource(R.string.height, pokemon.height.toString())
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }
}