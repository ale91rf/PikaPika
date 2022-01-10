package com.alejandroramirez.pikapika.ui.pokemondetail

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alejandroramirez.pikapika.R
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
        PokemonDetailAppBar(
            backgroundColor = appBarColor,
            modifier = Modifier.fillMaxWidth(),
            onBackPress = onBackPress
        )
        when {
            viewState.isLoading -> {
                FullScreenLoading()
            }
            viewState.error != null -> {
                PokemonDetailError(viewState.error)
            }
            viewState.pokemon != null -> {
                print(viewState.pokemon)
            }
        }
    }
}

@Composable
fun PokemonDetailError(error: PokemonDetailErrorType) {
    val context = LocalContext.current
    val message = when (error) {
        PokemonDetailErrorType.NETWORK -> stringResource(R.string.network_error)
        PokemonDetailErrorType.INVALID_ID -> stringResource(R.string.default_error)
    }
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

@Composable
private fun PokemonDetailAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
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
        title = { Text(text = "") },
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}