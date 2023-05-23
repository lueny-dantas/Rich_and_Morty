package paixao.lueny.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import paixao.lueny.rickandmorty.ui.theme.RickandMortyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickandMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RickandMortyApp()
                }
            }
        }
    }
}

@Composable
fun RickandMortyApp() {
    Text(
            text = "Hello!",
            modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RickandMortyAppPreview() {
    RickandMortyTheme {
        Surface {
            RickandMortyApp()
        }
    }
}