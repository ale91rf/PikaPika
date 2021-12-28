import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.alejandroramirez.pikapika.ui.home.HomeViewModel

@Composable
fun Home(
    navigateToPokemonDetail: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Surface(Modifier.fillMaxSize()) {

    }
}