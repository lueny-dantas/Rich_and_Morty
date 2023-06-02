package paixao.lueny.rickandmorty.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharacterDetailsScreen


const val characterDetailsRoute = "characterDetails"
internal val character = Character()

fun NavGraphBuilder.characterDetailsScreen(
    onPopBackStack: () -> Unit
) {
    composable(characterDetailsRoute) {
        CharacterDetailsScreen(
            character = character,
            onBackClick = onPopBackStack
        )
    }
}

fun NavController.navigateToCharacterDetails(navOptions: NavOptions? = null) {
    navigate(characterDetailsRoute, navOptions)
}
