package com.example.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.MainFlow ->
            navController.navigate(MainNavGraphDirections.actionGlobalMainFlow())
        NavigationFlow.CatalogueFlow ->
            navController.navigate(MainNavGraphDirections.actionGlobalCatalogueFlow())
        NavigationFlow.CartFlow ->
            navController.navigate(MainNavGraphDirections.actionGlobalCartFlow())

        is NavigationFlow.DetailsFlow ->
            navController.navigate(
                MainNavGraphDirections.actionGlobalDetailsFlow(
                    navigationFlow.detailsId
                )
            )
        is NavigationFlow.ProductByFlow ->
            navController.navigate(
                MainNavGraphDirections.actionGlobalProductByFlow(
                    navigationFlow.categoryId
                )
            )
    }
}