package com.crestinfosystems_jinay.wishlistapp

import androidx.compose.runtime.Composable
import com.crestinfosystems_jinay.wishlistapp.viewModel.WishViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.crestinfosystems_jinay.wishlistapp.screens.AddUpdataeDetail
import com.crestinfosystems_jinay.wishlistapp.screens.HomeScreen

@Composable
fun Navigation(
    viewModel: WishViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = ScreenRoute.HomeScreen.route) {
        composable(ScreenRoute.HomeScreen.route) {
            HomeScreen(navController, viewModel)
        }
        composable(ScreenRoute.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) {
            val id = if (it.arguments != null) it.arguments!!.getLong("id") else 0L
            AddUpdataeDetail(id = id, navController, viewModel)
        }
    }
}