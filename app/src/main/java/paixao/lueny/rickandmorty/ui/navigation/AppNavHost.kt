package paixao.lueny.rickandmorty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import paixao.lueny.rickandmorty.domain.models.Character

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = homeGraphRoute
    ) {
        homeGraph(
            onNavigateToCharacterDetails = { character ->
                navController.navigateToCharacterDetails(character)
            },
        )
        characterDetailsScreen(
            onPopBackStack = {
                navController.popBackStack()
            },
        )
    }
}






