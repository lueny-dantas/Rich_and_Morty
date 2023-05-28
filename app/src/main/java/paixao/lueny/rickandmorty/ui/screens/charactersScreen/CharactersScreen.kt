@file:OptIn(ExperimentalMaterial3Api::class)

package paixao.lueny.rickandmorty.ui.screens.charactersScreen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme
import paixao.lueny.rickandmorty.ui.theme.caveatFont
import paixao.lueny.rickandmorty.ui.theme.teal_700
import paixao.lueny.rickandmorty.ui.uiState.CharactersUiState

@Composable
fun CharactersScreen(
    characters: List<Character>,
    onCharacterClick: (Character) -> Unit = {},
    uiState: CharactersUiState = CharactersUiState(),
) {

   // val characters = uiState.characters
    val onFilterClick: () -> Unit = { }//abrir bottom sheet
    Column(
        Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

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

        LazyColumn(
            Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(characters) {
                CharacterCard(character = it)
            }
        }
    }
}

@Composable
private fun FilterButton(onClick: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.background(teal_700)
            ,
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
                characters = listOf()
            )
        }
    }

}