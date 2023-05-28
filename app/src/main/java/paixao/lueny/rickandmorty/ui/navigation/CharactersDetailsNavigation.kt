package paixao.lueny.rickandmorty.ui.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharactersDetailsScreen
import paixao.lueny.rickandmorty.ui.screens.characterDetailsScreen.CharactersDetailsViewModel

const val characterDetailsRoute = "characterDetails"

/*fun NavGraphBuilder.characterDetailsScreen(
    onPopBackStack: () -> Unit
) {
    composable(characterDetailsRoute) {
        val viewModel = //
            // viewModel<CharactersDetailsViewModel>(
            factory = CharactersDetailsViewModel.Factory
        )
        val uiState by viewModel.uiState.collectAsState()

        CharactersDetailsScreen(
            uiState = uiState,
            onBackClick = onPopBackStack
        )
    }
}

fun NavController.navigateToCharactersDetails(navOptions: NavOptions? = null) {
    navigate(charactersDetailsRoute, navOptions)
}*/
