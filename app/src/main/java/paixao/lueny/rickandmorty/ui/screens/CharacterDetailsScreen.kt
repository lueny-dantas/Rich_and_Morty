package paixao.lueny.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import paixao.lueny.rickandmorty.R
import paixao.lueny.rickandmorty.model.Character
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme

@Composable
fun CharactersDetailsScreen(
    character: Character,
    modifier: Modifier = Modifier,
) {
    Column(Modifier.fillMaxWidth()) {
        character.image?.let { image ->
            AsyncImage(
                image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(116.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop,
            )
            Text(text = character.name)
            Spacer(Modifier.height(16.dp))
        }
        Column(
            Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
        ) {

            Text(
                text = character.description,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.height(18.dp))

    }
}

@Preview
@Composable
fun CharactersDetailsScreenPreview() {
    RickandMortyTheme {
        CharactersDetailsScreen(character = Character("", "Rick", "Sem Descrição"))
    }


}
