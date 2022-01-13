package com.alejandroramirez.pikapika.ui.pokemondetail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.alejandroramirez.pikapika.R
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.ui.viewcomponent.FullScreenLoading

@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    onBackPress: () -> Unit
) {
    val viewState by viewModel.state.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        PokemonDetailContent(viewState = viewState, onBackPress = onBackPress)
    }
}

@Composable
fun PokemonDetailContent(
    viewState: PokemonDetailViewState,
    onBackPress: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val appBarColor = MaterialTheme.colors.primary
        //TODO extract this view logic by delegation or something like that
        when {
            viewState.isLoading -> {
                PokemonDetailAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = appBarColor,
                    onBackPress = onBackPress
                )
                FullScreenLoading()
            }
            viewState.error != null -> {
                PokemonDetailAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = appBarColor,
                    onBackPress = onBackPress
                )
                PokemonDetailError(viewState.error)
            }
            viewState.pokemon != null -> {
                PokemonDetailAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = viewState.pokemon.name,
                    backgroundColor = appBarColor,
                    onBackPress = onBackPress
                )
                PokemonDetail(viewState.pokemon)
            }
        }
    }
}

@Composable
fun PokemonDetailError(error: PokemonDetailErrorType) {
    val context = LocalContext.current
    //TODO extract this view logic by delegation or something like that
    val message = when (error) {
        PokemonDetailErrorType.NETWORK -> stringResource(R.string.network_error)
        PokemonDetailErrorType.INVALID_ID -> stringResource(R.string.default_error)
    }
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
private fun PokemonDetailAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    backgroundColor: Color,
    onBackPress: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_text)
                )
            }
        },
        title = { Text(text = title) },
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}

@Composable
private fun PokemonDetail(
    pokemon: Pokemon
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
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
            text = stringResource(R.string.weight, pokemon.weight.toString()),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 0.dp)
        )
        Text(
            text = stringResource(R.string.height, pokemon.height.toString()),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 0.dp)
        )
    }
}