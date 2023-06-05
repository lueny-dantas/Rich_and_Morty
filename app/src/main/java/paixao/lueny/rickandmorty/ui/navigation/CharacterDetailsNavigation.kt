package paixao.lueny.rickandmorty.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import paixao.lueny.rickandmorty.domain.models.Character
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharacterDetailsScreen
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharacterDetailsViewModel

const val characterDetailsRoute = "characterDetails"
internal const val characterIdArgument = "characterId"

fun NavGraphBuilder.characterDetailsScreen(
    onPopBackStack: () -> Unit
) {
    composable(
        "$characterDetailsRoute/{$characterIdArgument}"
    ) { backStackEntry ->
        val viewModel = viewModel<CharacterDetailsViewModel>(
            factory = CharacterDetailsViewModel.Factory
        )

        backStackEntry.arguments?.getString(characterIdArgument)?.let { currentCharacterId ->
            CharacterDetailsScreen(
                viewModel = viewModel,
                characterId = currentCharacterId.toInt(),
                onBackClick = onPopBackStack
            )
        }
    }
}

fun NavController.navigateToCharacterDetails(character: Character) {
    navigate("$characterDetailsRoute/${character.id}")
}
