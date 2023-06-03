@file:OptIn( ExperimentalMaterialApi::class)

package paixao.lueny.rickandmorty.ui.screens.charactersScreen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import paixao.lueny.rickandmorty.R
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme
import paixao.lueny.rickandmorty.ui.theme.caveatFont
import paixao.lueny.rickandmorty.ui.theme.white

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterBottomSheet(
    onFilterClick: (String?, Character.Status?) -> Unit,
    backContent: @Composable (() -> Unit) -> Unit,
) {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val openBottomSheet: () -> Unit = { scope.launch { state.show() } }

    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            BottomSheetContent(onFilterClick)
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
    onFilterClick: (String?, Character.Status?) -> Unit,
) {
    val context = LocalContext.current
    val searchText = remember { mutableStateOf("") }
    val selectedStatus = remember { mutableStateOf<Character.Status?>(null) }
    val onSearchTextChange: (String) -> Unit = { newText ->
        searchText.value = newText
    }

    val onChangeSelectedStatus: (Character.Status?) -> Unit = { newStatus ->
        selectedStatus.value = newStatus
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white)
            .padding(16.dp)
    ) {
        Text(
            context.getString(R.string.name_character),
            color = Color.Black,
            fontSize = 18.sp
        )
        SearchTextField(
            searchText = searchText.value,
            onSearchTextChange = onSearchTextChange,
            Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 3.dp)
        )

        Spacer(Modifier.height(16.dp))

        Text(
            context.getString(R.string.status_character),
            color = Color.Black,
            fontSize = 18.sp
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(white)
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 3.dp)
        ) {
            StatusButton(
                label = R.string.status_alive,
                status = Character.Status.Alive,
                onChangeSelectedStatus = onChangeSelectedStatus
            )
            StatusButton(
                label = R.string.status_dead,
                status = Character.Status.Dead,
                onChangeSelectedStatus = onChangeSelectedStatus
            )
            StatusButton(
                label = R.string.status_unknown,
                status = Character.Status.Unknown,
                onChangeSelectedStatus = onChangeSelectedStatus
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(white)
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 3.dp)
        ) {
            Text(
                text = context.getString(R.string.status_selected),
                color = Color.Black,
                fontSize = 18.sp
            )
            Text(
                text = context.getString(R.string.status_alive),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp
            )
            CleanButton(
                label = R.string.status_clean,
                onChangeSelectedStatus = onChangeSelectedStatus
            )
        }
        Spacer(Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 3.dp),
            border = BorderStroke(0.5.dp, Color.White),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = {
                onFilterClick(searchText.value, selectedStatus.value)
            }
        ) {
            Text(context.getString(R.string.filter))
        }
    }
}

@Composable
private fun CleanButton (
    label: Int,
    onChangeSelectedStatus: (Character.Status?) -> Unit,
) {
    val context = LocalContext.current
    Button(
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(
            containerColor = white,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier.padding(vertical = 3.dp, horizontal = 3.dp),
        onClick = { onChangeSelectedStatus(null) }

    ) {
        Text(
            text = context.getString(label),
            modifier = Modifier.padding(start = 4.dp),
            color = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
private fun StatusButton(
    label: Int,
    status: Character.Status? = null,
    onChangeSelectedStatus: (Character.Status?) -> Unit,
) {
    val context = LocalContext.current
    Button(
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(
            containerColor = white,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        onClick = { onChangeSelectedStatus(status) }

    ) {
        Text(
            text = context.getString(label),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun SearchTextField(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue -> onSearchTextChange(newValue) },
        shape = RoundedCornerShape(100),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
        ),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Icone de pesquisa") },
        placeholder = { androidx.compose.material.Text(text = context.getString(R.string.search_bar)) },

    )

}

@Preview
@Composable
private fun PreviewBottomSheet() {
    RickandMortyTheme {
       BottomSheetContent(
            onFilterClick = { _, _ -> }
        )

    }
}
