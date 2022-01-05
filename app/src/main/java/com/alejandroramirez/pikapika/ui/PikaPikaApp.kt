import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejandroramirez.pikapika.ui.PikaPikaAppState
import com.alejandroramirez.pikapika.ui.Screen
import com.alejandroramirez.pikapika.ui.rememberPikaPikaAppState
import com.alejandroramirez.pikapika.ui.pokemondetail.PokemonDetailScreen

@Composable
fun PikaPikaApp(
    appState: PikaPikaAppState = rememberPikaPikaAppState()
) {
    NavHost(navController = appState.navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { backStackEntry ->
            HomeScreen(
                navigateToPokemonDetail = { pokemonId ->
                    appState.navigateToPokemonDetail(pokemonId, backStackEntry)
                }
            )
        }
        composable(Screen.PokemonDetail.route) { backStackEntry ->
            PokemonDetailScreen(onBackPress = appState::navigateBack)
        }
    }
}