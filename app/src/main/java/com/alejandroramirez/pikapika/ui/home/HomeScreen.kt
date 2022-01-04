import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alejandroramirez.pikapika.ui.home.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.alejandroramirez.pikapika.R
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.ui.home.PokemonList

@Composable
fun HomeScreen(
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        val appBarColor = MaterialTheme.colors.primary
        HomeAppBar(
            backgroundColor = appBarColor,
            modifier = Modifier.fillMaxWidth()
        )
        PokemonList(pokemons = pokemons, navigateToPokemonDetail = navigateToPokemonDetail)
    }
}

@Composable
fun HomeAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}