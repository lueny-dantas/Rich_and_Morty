package paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import paixao.lueny.rickandmorty.R
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme
import paixao.lueny.rickandmorty.ui.theme.caveatFont
import paixao.lueny.rickandmorty.ui.theme.teal_700
import paixao.lueny.rickandmorty.ui.theme.white

@Composable
fun CharacterDetailsScreen(
    character: Character,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        ToolBar(
            context = context,
            onBackClick = onBackClick
        )
        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            placeholder = painterResource(id = R.drawable.placeholder),
            contentScale = ContentScale.Crop,
        )
        Column(
            Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        ) {
            CharacterName(character)
            Information(label = R.string.status, value = character.status.statusPresentation)
            Information(label = R.string.species, value = character.species)
            Information(label = R.string.gender, value = character.gender.genderApresentation)
            Information(label = R.string.origin, value = character.origin.name)
            Information(label = R.string.location, value = character.location.name)
        }
    }
}

@Composable
private fun CharacterName(character: Character) {
    Text(
        text = character.name,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun Information(
    label: Int,
    value: String,
) {
    val context = LocalContext.current
    Row {
        Text(text = context.getString(label))
        Spacer(modifier = Modifier.weight(1F))
        Text(text = value)
    }
    Spacer(Modifier.height(16.dp))
}

@Composable
private fun ToolBar(
    context: Context,
    onBackClick: () -> Unit = {}
) {
    Surface {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = white,
                )
            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
            Spacer(modifier = Modifier.weight(0.8F))
            Text(
                text = context.getString(R.string.details_characters),
                color = Color.White,
                fontFamily = caveatFont,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,

                )
            Spacer(modifier = Modifier.weight(1F))

        }
    }
}

@Composable
fun CharacterDetails(text: String) {
    Column {

    }
}


@Preview
@Composable
fun CharactersDetailsScreenPreview() {
    RickandMortyTheme {
        CharacterDetailsScreen(
            character =
            Character(
                0,
                "Rick Sanchez",
                Character.Status.Alive,
                "Humano",
                Character.Gender.Male,
                Character.Origin("Terra"),
                Character.Location("Terra"),
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",

                ),
            onBackClick = {}
        )

    }
}
