package paixao.lueny.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import paixao.lueny.rickandmorty.data.retrofitBuilder.ApiCharacterInfrastructure
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.screens.charactersScreen.CharactersScreen
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
                    RickandMortyApp()
                }
            }
        }
    }
}


@Composable
fun RickandMortyApp() {

    val service = remember {
        ApiCharacterInfrastructure()
    }

    val characters = remember {
        MutableStateFlow<List<Character>>(emptyList())
    }
    val charactersResult = characters.collectAsState().value

    LaunchedEffect(Unit) {
        runCatching {
            service.getCharacters()
        }.fold(
            onSuccess = {
                characters.emit(it)
            },
            onFailure = {
                println(it)
            }
        )
    }

    Column {
        CharactersScreen(charactersResult)
    }
}

/*@Composable
fun HomeScreen (
    homeScreenUiState: HomeScreenUiState,
    charactersUistate:CharactersUiState,
    modifier: Modifier = Modifier
    ) {
        when(homeScreenUiState){
            is HomeScreenUiState.Loading -> LoadingScreen(modifier)
            is HomeScreenUiState.Success -> ResultScreen(charactersUistate, modifier)
            is HomeScreenUiState. Error -> ErrorScreen (modifier)
        }

}*/

@Composable
fun ResultScreen(charactersUistate: Any, modifier: Modifier) {

}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading),
            contentDescription = stringResource(R.string.loading)
        )
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