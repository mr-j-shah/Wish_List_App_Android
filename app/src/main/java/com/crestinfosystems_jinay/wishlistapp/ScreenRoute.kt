package com.crestinfosystems_jinay.wishlistapp

sealed class ScreenRoute(val route:String) {
    object HomeScreen : ScreenRoute("HomeScreen")
    object AddScreen :ScreenRoute("AddScreen")

}