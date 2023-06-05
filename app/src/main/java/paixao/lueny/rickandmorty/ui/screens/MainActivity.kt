package paixao.lueny.rickandmorty.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import paixao.lueny.rickandmorty.ui.navigation.AppNavHost
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

    RickAndMortyApp(
        content = { AppNavHost(navController = navController) }
    )


}

@Composable
fun RickAndMortyApp(
    content: @Composable () -> Unit,
) {
    RickandMortyTheme {
        Box{
            content()
        }
    }
}