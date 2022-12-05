package com.example.navigation

import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions

fun buildDeepLink(destination: DeepLinkDestination) =
    NavDeepLinkRequest.Builder
        .fromUri(destination.address.toUri())
        .build()

fun NavController.deepLinkNavigateTo(
    deepLinkDestination: DeepLinkDestination,
    popUpTo: Boolean = false
) {
    val builder = NavOptions.Builder()

    if (popUpTo) {
        builder.setPopUpTo(graph.startDestinationId, true)
    }

    navigate(
        buildDeepLink(deepLinkDestination),
        builder.build()
    )
}

sealed class DeepLinkDestination(val address: String) {
    class Details(product_details_id: Int) :
        DeepLinkDestination("example://details/exampleArgs?msg=${product_details_id}")
    class ProductBy(category_id: Int) :
            DeepLinkDestination("example://product_by/exampleArgs?msg=${category_id}")
    object Main: DeepLinkDestination("example://main")
}