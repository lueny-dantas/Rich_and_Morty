package paixao.lueny.rickandmorty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeGraphRoute
    ) {
        homeGraph(
            onNavigateToCharacterDetails = {
                navController.navigateToCharacterDetails()
            },
        )
        characterDetailsScreen(
            onPopBackStack = {
                navController.navigateToCharacters()
            },
        )
    }
}