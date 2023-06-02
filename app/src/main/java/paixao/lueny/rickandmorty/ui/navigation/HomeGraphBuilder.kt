package paixao.lueny.rickandmorty.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import paixao.lueny.rickandmorty.domain.models.Character

internal const val homeGraphRoute = "home"
fun NavGraphBuilder.homeGraph(
    onNavigateToCharacterDetails:(Character) -> Unit
) {
    navigation(
        startDestination = charactersRoute,
        route = homeGraphRoute
    ){
        charactersScreen(onNavigateToCharacterDetails)
    }
}

fun NavController.navigateToHomeGraph(){
    navigate(homeGraphRoute)
}





