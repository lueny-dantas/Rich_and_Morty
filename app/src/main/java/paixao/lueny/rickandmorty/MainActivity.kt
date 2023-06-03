package paixao.lueny.rickandmorty

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.navigation.AppNavHost
import paixao.lueny.rickandmorty.ui.navigation.navigateToCharacterDetails
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
                    RickAndMortyAppController()
                }
            }
        }
    }
}

@Composable
fun RickAndMortyAppController() {
    val navController: NavHostController = rememberNavController()

    LaunchedEffect(Unit) {
        navController.addOnDestinationChangedListener { _, _, _ ->
            val routes = navController.backQueue.map {
                it.destination.route
            }
            Log.i("MainActivity", "onCreate: back stack - $routes")
        }
    }

    RickAndMortyApp(
        onCharacterSelected = { character ->
            navController.navigateToCharacterDetails(character)
        },
        onBackCharacters = {navController.popBackStack()}
    ) {
        AppNavHost(navController = navController)
    }


}

@Composable
fun RickAndMortyApp(
    onCharacterSelected: (Character) -> Unit = {},
    onBackCharacters: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    RickandMortyTheme {
        Box (
        ) {
            content()
        }
    }
}