import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alejandroramirez.pikapika.ui.home.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.alejandroramirez.pikapika.R
import com.alejandroramirez.pikapika.domain.model.Pokemon
import com.alejandroramirez.pikapika.ui.home.HomeViewState
import com.alejandroramirez.pikapika.ui.home.PokemonList
import com.alejandroramirez.pikapika.ui.viewcomponent.FullScreenLoading

@Composable
fun HomeScreen(
    navigateToPokemonDetail: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState by viewModel.state.collectAsState()
    Surface(Modifier.fillMaxSize()) {
        HomeContent(
            viewState = viewState,
            navigateToPokemonDetail = navigateToPokemonDetail
        )
    }
}

@Composable
fun HomeContent(
    viewState: HomeViewState,
    navigateToPokemonDetail: (Int) -> Unit
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
        if (viewState.isLoading) {
            FullScreenLoading()
        } else {
            PokemonList(pokemons = viewState.pokemons, navigateToPokemonDetail = navigateToPokemonDetail)
        }
    }
}

@Composable
private fun HomeAppBar(
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