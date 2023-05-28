package paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import paixao.lueny.rickandmorty.R
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme
import paixao.lueny.rickandmorty.ui.theme.caveatFont
import paixao.lueny.rickandmorty.ui.theme.teal_700

@Composable
fun CharactersDetailsScreen(
    character: Character,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Surface {
            Row(
                modifier = Modifier
                    .background(teal_700)
                    .padding(12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Icon(
                    Icons.Rounded.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    )
                Spacer(modifier = Modifier.weight(0.8F))
                Text(
                    text = "Detalhes",
                    color = Color.White,
                    fontFamily = caveatFont,
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,

                )
                Spacer(modifier = Modifier.weight(1F))

            }
        }

        AsyncImage(
            model = character.image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(116.dp),
            placeholder = painterResource(id = R.drawable.placeholder),
            contentScale = ContentScale.Crop,
        )
        Column(
            Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        ) {
            Text(text = character.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold )
            Spacer(Modifier.height(16.dp))
            Text(text = character.status.statusPresentation)
            Spacer(Modifier.height(16.dp))
            Text(text = character.species)
            Spacer(Modifier.height(16.dp))
            Text(text = character.gender.genderApresentation)
            Spacer(Modifier.height(16.dp))
            Text(text = character.origin.name)
            Spacer(Modifier.height(16.dp))
            Text(text = character.location.name)

        }
    }
}


@Preview
@Composable
fun CharactersDetailsScreenPreview() {
    RickandMortyTheme {
        CharactersDetailsScreen(
            character =
            Character(
                "Rick Sanchez",
                Character.Status.Alive,
                "Human",
                Character.Gender.Male,
                Character.Origin("Earth"),
                Character.Location("Earth"),
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            )
        )

    }
}
