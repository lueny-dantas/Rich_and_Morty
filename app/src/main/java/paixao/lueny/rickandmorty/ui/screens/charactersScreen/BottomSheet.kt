@file:OptIn(ExperimentalMaterialApi::class)

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
    onFilterClick: (String, Character.Status?) -> Unit,
    backContent: @Composable (() -> Unit) -> Unit,
) {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    val openBottomSheet: () -> Unit = { scope.launch { state.show() } }

    val newOnFilterClick: (String, Character.Status?) -> Unit = { searchText, status ->
        scope.launch { state.hide() }
        onFilterClick(searchText, status)
    }
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            BottomSheetContent(newOnFilterClick)
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
    onFilterClick: (String, Character.Status?) -> Unit,
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

    val cleanSearchText: () -> Unit = {
        searchText.value = ""
    }

    val statusText = if (selectedStatus.value != null) {
        selectedStatus.value!!.statusPresentation
    } else {
        context.getString(R.string.status_not_selected)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Text(
            context.getString(R.string.name_character),
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 18.sp
        )
        Spacer(Modifier.height(8.dp))

        SearchTextField(
            searchText = searchText.value,
            onSearchTextChange = onSearchTextChange,
        )

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = context.getString(R.string.status_character),
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 18.sp
            )

            Text(
                text = statusText,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
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

        CleanButton(
            label = R.string.status_clean,
            onChangeSelectedStatus = onChangeSelectedStatus,
            cleanSearchText = cleanSearchText
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp, horizontal = 3.dp),
            border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.secondary),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            onClick = {
                onFilterClick(searchText.value, selectedStatus.value)
            }
        ) {
            Text(
                color = MaterialTheme.colorScheme.secondary,
                text = context.getString(R.string.filter),
            )
        }
    }
}

@Composable
private fun SearchTextField(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
) {
    val context = LocalContext.current

    OutlinedTextField(
        value = searchText,
        onValueChange = { newValue -> onSearchTextChange(newValue) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(100),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.secondary,

            ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Icone de pesquisa",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        placeholder = {
            Text(
                text = context.getString(R.string.search_bar),
                color = MaterialTheme.colorScheme.primary
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        )
    )

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
private fun CleanButton(
    label: Int,
    onChangeSelectedStatus: (Character.Status?) -> Unit,
    cleanSearchText: () -> Unit
) {
    val context = LocalContext.current
    Button(
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.primary),
        colors = ButtonDefaults.buttonColors(
            containerColor = white,
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 3.dp),
        onClick = {
            onChangeSelectedStatus(null)
            cleanSearchText()
        }

    ) {
        Text(
            text = context.getString(label),
            color = MaterialTheme.colorScheme.primary
        )
    }

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
