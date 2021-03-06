package com.alejandroramirez.pikapika.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alejandroramirez.pikapika.ui.Arg.POKEMON_ID_ARGUMENT

object Arg {
    const val POKEMON_ID_ARGUMENT = "pokemonId"
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object PokemonDetail : Screen("pokemon/{$POKEMON_ID_ARGUMENT}") {
        fun createRoute(pokemonId: Int) = "pokemon/$pokemonId"
    }
}

@Composable
fun rememberPikaPikaAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    PikaPikaAppState(navController)
}

class PikaPikaAppState(
    val navController: NavHostController
) {
    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToPokemonDetail(pokemonId: Int, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.PokemonDetail.createRoute(pokemonId))
        }
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED