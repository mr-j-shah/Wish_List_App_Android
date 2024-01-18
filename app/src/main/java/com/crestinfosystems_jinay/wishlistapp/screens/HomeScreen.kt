package com.crestinfosystems_jinay.wishlistapp.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState



import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.crestinfosystems_jinay.wishlistapp.ScreenRoute
import com.crestinfosystems_jinay.wishlistapp.utils.ColorUtils
import com.crestinfosystems_jinay.wishlistapp.viewModel.WishViewModel
import com.crestinfosystems_jinay.wishlistapp.widgets.AppBar
import com.crestinfosystems_jinay.wishlistapp.widgets.wishItem


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: WishViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBar(title = "Wish List")
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(20.dp),
                onClick = {
                    navController.navigate(ScreenRoute.AddScreen.route + "/0")
                },
                contentColor = ColorUtils.textColor, // Icon color
                backgroundColor = ColorUtils.secondaryBakgroundColor,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        backgroundColor = ColorUtils.primaryBackGroundColor,
    ) {
        Column(modifier = Modifier.padding(it)) {
            val wishListData = viewModel.getAllWishes.collectAsState(initial = listOf())
            LazyColumn() {
                items(wishListData.value, key = { wish -> wish.id }) { wish ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                                viewModel.deleteWish(wish)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        background = {
                            val color by animateColorAsState(
                                targetValue = if (
                                    dismissState.dismissDirection == DismissDirection.EndToStart
                                    || dismissState.dismissDirection == DismissDirection.StartToEnd
                                )
                                    ColorUtils.dismissColor
                                else Color.Transparent,
                                label = ""
                            )
                            Card(
                                shape = RoundedCornerShape(15.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                                    .align(Alignment.CenterVertically),
                                backgroundColor = color,
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    horizontalAlignment =
                                    if (dismissState.dismissDirection == DismissDirection.EndToStart)
                                        Alignment.End
                                    else
                                        Alignment.Start
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = null)
                                }
                            }
                        },
                        dismissThresholds = { FractionalThreshold(0.25f) },
                        directions = setOf(
                            DismissDirection.EndToStart,
                            DismissDirection.StartToEnd
                        ),
                    ) {
                        wishItem(wish = wish) {
                            navController.navigate(ScreenRoute.AddScreen.route + "/${wish.id}")
                        }
                    }

                }
            }
        }
    }

}

