package paixao.lueny.rickandmorty.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.screens.charactersScreen.CharactersViewModel
import paixao.lueny.rickandmorty.ui.screens.charactersScreen.CharactersScreen

internal const val  charactersRoute = "characters"

fun NavGraphBuilder.charactersScreen(
    onNavigateToCharacterDetails:(Character) -> Unit
) {
    composable(charactersRoute){
        val viewModel = viewModel<CharactersViewModel>()
        CharactersScreen(
            viewModel = viewModel,
            onCharacterClick = onNavigateToCharacterDetails
        )
    }
}

fun NavController.navigateToCharacters(navOptions: NavOptions? = null){
    navigate(charactersRoute, navOptions)
}
