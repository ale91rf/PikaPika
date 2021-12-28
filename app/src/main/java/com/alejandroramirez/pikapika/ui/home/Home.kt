import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alejandroramirez.pikapika.ui.home.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.ui.home.PokemonList

@Composable
fun Home(
    navigateToPokemonDetail: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        HomeContent(
            pokemons = viewState.pokemons,
            navigateToPokemonDetail = navigateToPokemonDetail
        )
    }
}

@Composable
fun HomeContent(
    pokemons: List<Pokemon>,
    navigateToPokemonDetail: (String) -> Unit
) {
    PokemonList(pokemons = pokemons, navigateToPokemonDetail = navigateToPokemonDetail)
}