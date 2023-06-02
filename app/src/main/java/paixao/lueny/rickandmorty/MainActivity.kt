package paixao.lueny.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import paixao.lueny.rickandmorty.ui.screens.charactersScreen.CharactersScreen
import paixao.lueny.rickandmorty.ui.screens.charactersScreen.CharactersViewModel
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme
import androidx.compose.material3.CenterAlignedTopAppBar as CenterAlignedTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RickandMortyApp()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RickandMortyApp() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                { Text(text = context.getString(R.string.app_name)) },
                Modifier.semantics { testTag = "RickAndMortyTopAppBar" })
        })
    {
        Column(
            Modifier.padding(it)
        ) {
            CharactersScreen(
                viewModel = CharactersViewModel(),
                oncharacterClick = {}
            )

        }
    }

}


@Preview(showBackground = true)
@Composable
fun RickandMortyAppPreview() {
    RickandMortyTheme {
        Surface {
            RickandMortyApp()
        }
    }
}