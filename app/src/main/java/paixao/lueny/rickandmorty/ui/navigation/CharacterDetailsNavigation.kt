package paixao.lueny.rickandmorty.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharacterDetailsScreen


const val characterDetailsRoute = "characterDetails"
internal const val characterIdArgument = "characterId"

fun NavGraphBuilder.characterDetailsScreen(
    character: Character,
    onPopBackStack: () -> Unit
) {
    composable(
        "$characterDetailsRoute/{$characterIdArgument}"
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(characterIdArgument)?.let {
            CharacterDetailsScreen(
                character = character,
                onBackClick = onPopBackStack
            )
        }
    }
}

fun NavController.navigateToCharacterDetails(id: Any?) {
    navigate("$characterDetailsRoute/$id")
}
