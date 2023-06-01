@file:OptIn(ExperimentalMaterial3Api::class)

package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.TextStyle
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
import paixao.lueny.rickandmorty.ui.theme.teal_700
import androidx.compose.foundation.layout.Column as Column

@Composable
fun CharactersScreen(
    onCharacterClick: (Character) -> Unit = {},
) {
    val viewModel = remember { CharactersListViewModel() }

    val characters = viewModel.getCharacters().collectAsLazyPagingItems()

    val onFilterClick: () -> Unit = { }//abrir bottom sheet

    Column(
        Modifier.fillMaxSize()
    ) {
        val context = LocalContext.current
        Toolbar(context, onFilterClick)
        CharacterList(characters = characters)

    }
}

@Composable
private fun Toolbar(context: Context, onFilterClick: () -> Unit) {
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
                text = context.getString(R.string.characters),
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
private fun CharacterList(characters: LazyPagingItems<Character>) {

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(characters) { character ->
            character?.let {
                CharacterItem(character = character)
            }
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
                text = context.getString(R.string.filter),
                fontSize = 12.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}


@Composable
fun BottomSheet() {
    val context = LocalContext.current
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { isBottomSheetVisible = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(context.getString(R.string.open_filter))
        }

        if (isBottomSheetVisible) {
            FilterBottomSheet(
                onClose = { isBottomSheetVisible = false },
                onApplyFilter = { /* Lógica para aplicar o filtro */ })
        }
    }

}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterBottomSheet(onClose: () -> Unit, onApplyFilter: () -> Unit) {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetState = state ,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Filter Options",
                    style = TextStyle(fontSize = 20.sp),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Aqui você pode adicionar opções de filtro, como caixas de seleção, sliders, etc.
                // Exemplo:
                Checkbox(
                    checked = true,
                    onCheckedChange = { /* Lógica para tratar a seleção do filtro */ },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        scrimColor = Color.Black.copy(alpha = 0.5f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Button(
                onClick = {
                    onApplyFilter()
                    onClose()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Apply Filter")
            }
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