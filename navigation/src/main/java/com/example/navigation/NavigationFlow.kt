package com.example.navigation

sealed class NavigationFlow {
    object MainFlow : NavigationFlow()
    object CatalogueFlow : NavigationFlow()
    object CartFlow : NavigationFlow()
    class DetailsFlow(val detailsId: Int) : NavigationFlow()
}