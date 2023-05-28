@file:OptIn(ExperimentalMaterial3Api::class)

package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme
import paixao.lueny.rickandmorty.ui.theme.caveatFont
import paixao.lueny.rickandmorty.ui.theme.teal_700
import paixao.lueny.rickandmorty.ui.uiState.CharactersListUiState

@Composable
fun CharactersScreen(
    onCharacterClick: (Character) -> Unit = {},
) {
    val viewModel = remember { CharactersListViewModel() }

    val uiState = viewModel.uiState.collectAsState().value

    val onFilterClick: () -> Unit = { }//abrir bottom sheet

    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        Toolbar(context, onFilterClick)
        CharacterList(viewModel, uiState, Modifier.weight(1f))
        Row {
            Button(modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = teal_700),
                onClick = { coroutineScope.launch {
                    viewModel.getCharacters(uiState.paginationState.currentPage + 1)
                } }) {
                Text(text = "Próxima Página")
            }
        }

    }
}

@Composable
private fun Toolbar(context: Context, onFilterClick: () -> Unit) {
    Surface {
        Row(
            modifier = Modifier
                .background(teal_700)
                .padding(12.dp)
                .fillMaxWidth()
                .clickable {

                    Toast
                        .makeText(context, "clickou!!", Toast.LENGTH_SHORT)
                        .show()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(
                text = "Personagens",
                color = Color.White,
                fontFamily = caveatFont,
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.weight(1F))

            FilterButton(onClick = onFilterClick)
        }
    }
}

@Composable
private fun CharacterList(
    viewModel: CharactersListViewModel,
    uiState: CharactersListUiState,
    modifier: Modifier,
) {
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit){
        viewModel.getCharacters(1)
    }

    LaunchedEffect(lazyListState) {
        val visibleItemCount = lazyListState.layoutInfo.visibleItemsInfo.size
        val totalItemCount = lazyListState.layoutInfo.totalItemsCount
        val firstVisibleItemIndex = lazyListState.firstVisibleItemIndex

        if (visibleItemCount > 0 && firstVisibleItemIndex + visibleItemCount >= totalItemCount) {
            // O usuário chegou ao final do scroll, então atualizamos a página atual
            viewModel.getCharacters(uiState.paginationState.currentPage + 1)
        }
    }

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(uiState.characters) {
            CharacterCard(character = it)
        }
    }
}

@Composable
private fun FilterButton(onClick: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.background(teal_700),
        border = BorderStroke(0.5.dp, Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(teal_700)
                .padding(vertical = 2.dp, horizontal = 6.dp)
                .clickable {
                    onClick()
                    Toast
                        .makeText(context, "clickou!!", Toast.LENGTH_SHORT)
                        .show()
                }
        ) {
            Icon(
                Icons.Rounded.List,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Filtrar",
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun CharactersScreenPreview() {
    RickandMortyTheme {
        Surface {
            CharactersScreen(
                onCharacterClick = {}
            )
        }
    }

}