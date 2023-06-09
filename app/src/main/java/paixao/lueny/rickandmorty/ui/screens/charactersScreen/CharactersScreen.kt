@file:OptIn(ExperimentalMaterial3Api::class)

package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import paixao.lueny.rickandmorty.R
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme
import paixao.lueny.rickandmorty.ui.theme.caveatFont
import androidx.compose.foundation.layout.Column as Column

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel,
    onCharacterClick: (Character) -> Unit
) {
    val textFilter = remember { mutableStateOf<String?>(null) }
    val statusFilter = remember { mutableStateOf<Character.Status?>(null) }

    val characters = remember(textFilter.value, statusFilter.value) {
        viewModel.getCharacters(textFilter = textFilter.value, statusFilter = statusFilter.value)
    }.collectAsLazyPagingItems()

    val onFilterClick: (String, Character.Status?) -> Unit = { newSearchText, newStatus ->
        if (newSearchText != "") {
            textFilter.value = newSearchText
        } else {
            textFilter.value = null
        }

        if (newStatus != null) {
            statusFilter.value = newStatus
        } else {
            statusFilter.value = null
        }
    }
    RickandMortyTheme {
        FilterBottomSheet(
            onFilterClick = onFilterClick,
            backContent = { openBottomSheet ->
                Column(
                    Modifier.fillMaxSize()
                ) {
                    Toolbar(openBottomSheet = openBottomSheet)
                    CharacterList(
                        characters = characters,
                        onCharacterClick = onCharacterClick
                    )
                }

            }
        )
    }
}

@Composable
private fun Toolbar(openBottomSheet: () -> Unit) {
    val context = LocalContext.current

    Surface {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Text(
                text = context.getString(R.string.characters),
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = caveatFont,
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.weight(1F))
            FilterButton(openBottomSheet = openBottomSheet)
        }
    }
}


@Composable
private fun CharacterList(
    characters: LazyPagingItems<Character>,
    onCharacterClick: (Character) -> Unit = {}
) {

    LazyColumn(
        Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(characters) { character ->
            character?.let {
                CharacterItem(
                    character = character,
                    Modifier
                        .clickable { onCharacterClick(it) })
            }
        }
    }
}


@Composable
private fun FilterButton(openBottomSheet: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        border = BorderStroke(0.5.dp, Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 2.dp, horizontal = 6.dp)
                .clickable {
                    openBottomSheet()
                }
        ) {
            Icon(
                Icons.Rounded.List,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = context.getString(R.string.filter),
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
                viewModel = CharactersViewModel(),
                onCharacterClick = { }
            )
        }
    }

}