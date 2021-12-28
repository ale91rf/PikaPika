import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alejandroramirez.pikapika.Home
import com.alejandroramirez.pikapika.PikaPikaAppState
import com.alejandroramirez.pikapika.Screen
import com.alejandroramirez.pikapika.rememberPikaPikaAppState

@Composable
fun PikaPikaApp(
    appState: PikaPikaAppState = rememberPikaPikaAppState()
) {
    NavHost(navController = appState.navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { backStackEntry ->
            Home(
                navigateToPokemonDetail = { pokemonId ->
                    appState.navigateToPokemonDetail(pokemonId, backStackEntry)
                }
            )
        }
    }
}