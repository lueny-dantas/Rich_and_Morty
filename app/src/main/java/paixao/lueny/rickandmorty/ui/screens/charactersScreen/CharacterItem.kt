package paixao.lueny.rickandmorty.ui.screens.charactersScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import paixao.lueny.rickandmorty.R
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row {
            AsyncImage(
                model = character.image,
                contentDescription = null,
                Modifier
                    .width(80.dp)
                    .fillMaxHeight(),
                error = painterResource(id = R.drawable.error),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .padding(16.dp)
                    .weight(3f)
            ) {
                Text(
                    text = character.name,
                    Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )
            }
        }
    }
}

@Preview
@Composable
fun CharacterCardPreview() {
    RickandMortyTheme {
        CharacterItem(
            character = Character(
                id = 1,
                name = "Rick Sanchez",
                status = Character.Status.Alive,
                species = "Human",
                gender = Character.Gender.Male,
                origin = Character.Origin("Earth"),
                location = Character.Location("Earth"),
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            )
        )
    }

}