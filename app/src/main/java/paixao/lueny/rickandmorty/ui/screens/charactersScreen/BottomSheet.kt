package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import paixao.lueny.rickandmorty.R
import paixao.lueny.rickandmorty.ui.theme.caveatFont
import paixao.lueny.rickandmorty.ui.theme.teal_700
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharacterDetailsUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterBottomSheet(onApplyFilter: () -> Unit, backContent: @Composable (() -> Unit) -> Unit) {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val openBottomSheet: () -> Unit = { scope.launch { state.show() } }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            BottomSheetContent()
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        scrimColor = Color.Black.copy(alpha = 0.5f),
        content = {
            backContent(openBottomSheet)
        }
    )
}

@Composable
private fun BottomSheetContent(
    state: CharacterDetailsUiState = CharacterDetailsUiState()
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = teal_700)
            .padding(16.dp)
    ) {
        Text(
            context.getString(R.string.name_character),
            color = Color.White,
            fontFamily = caveatFont,
            fontSize = 32.sp
        )
        SearchTextField(
            searchText = state.searchText,
            onSearchChange = state.onSearchChange,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 6.dp)

        )

        Spacer(Modifier.height(20.dp))

        Text(
            context.getString(R.string.status_character),
            color = Color.White,
            fontFamily = caveatFont,
            fontSize = 32.sp
        )


        Spacer(Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(teal_700)
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 6.dp),

        ) {
            Button(
                border = BorderStroke(0.5.dp, Color.White),
                onClick = {}
            ) {
                Text(
                    text = context.getString(R.string.status_alive),
                )
            }
            Button(
                border = BorderStroke(0.8.dp, Color.White),
                onClick = {}
            ) {
                Text(text = context.getString(R.string.status_dead))
            }
            Button(
                border = BorderStroke(0.5.dp, Color.White),
                onClick = { /*TODO*/ }
            ) {
                Text(text = context.getString(R.string.status_unknown))
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(
            modifier= Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 6.dp),
            border = BorderStroke(0.8.dp, Color.White),
            onClick = {}
        ) {
            Text(context.getString(R.string.filter))
        }
        Spacer(Modifier.height(20.dp))

    }
}

@Composable
fun SearchTextField(
    searchText: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue -> onSearchChange(newValue) },
        modifier,
        shape = RoundedCornerShape(100),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Icone de pesquisa") },
        label = {
            androidx.compose.material.Text(
                text = context.getString(R.string.character),
                color = Color.White
            )
        },
        placeholder = { androidx.compose.material.Text(text = context.getString(R.string.search_bar)) }
    )

}

@Preview
@Composable
private fun PreviewBottomSheet() {
    BottomSheetContent()
}
