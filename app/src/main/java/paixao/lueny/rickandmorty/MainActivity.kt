package paixao.lueny.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.navigation.AppNavHost
import paixao.lueny.rickandmorty.ui.navigation.characterDetailsRoute
import paixao.lueny.rickandmorty.ui.navigation.charactersRoute
import paixao.lueny.rickandmorty.ui.navigation.navigateToCharacterDetails
import paixao.lueny.rickandmorty.ui.navigation.navigateToCharacters
import paixao.lueny.rickandmorty.ui.screens.charactersScreen.CharactersScreen
import paixao.lueny.rickandmorty.ui.screens.charactersScreen.CharactersViewModel
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RickandMortyAppController()
                }
            }
        }
    }
}

@Composable
fun RickandMortyAppController(navController: NavHostController = rememberNavController()) {

    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, _, _ ->
            val routes = navController.backQueue.map {
                it.destination.route
            }
            Log.i("MainActivity", "onCreate: back stack - $routes")
        }
    }
    val backStackEntryState by navController.currentBackStackEntryAsState()
    val orderDoneMessage = backStackEntryState
        ?.savedStateHandle
        ?.getStateFlow<String?>("order_done", null)
        ?.collectAsState()
    Log.i(
        "MainActivity",
        "onCreate: order done message ${orderDoneMessage?.value} "
    )
    val currentDestination = backStackEntryState?.destination
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    orderDoneMessage?.value?.let { message ->
        scope.launch {
            snackbarHostState.showSnackbar(message = message)
        }
    }
    val currentRoute = currentDestination?.route
    val selectedItem by remember(currentDestination) {
        when (currentRoute) {
            charactersRoute ->// adicionar o caminho
                characterDetailsRoute-> //adicionar o caminho
        } else ->
    }
    RickandMortyApp(
        onCharacterSelected = {character ->
            navController.navigateToCharacterDetails(character)
        },
        onBackCharacters = {navController.navigateToCharacters()}
    ) {
        AppNavHost(navController = navController, character =//??)
    }


}

@Composable
fun RickandMortyApp(
    onCharacterSelected: (Character?) -> Unit = {},
    onBackCharacters: () -> Unit = {},
    function: () -> Unit,
) {
    RickandMortyTheme {
        Column(
        ) {
            CharactersScreen(
                viewModel = CharactersViewModel(),
                onCharacterClick = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RickandMortyAppPreview() {
    RickandMortyTheme {
        Surface {
            RickandMortyApp(onBackCharacters = {
                AppNavHost(navController = navController)
            }) {
                paixao.lueny.rickandmorty.ui.navigation.AppNavHost(navController = navController)
            }
        }
    }
}