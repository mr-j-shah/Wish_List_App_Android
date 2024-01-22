package com.crestinfosystems_jinay.wishlistapp.widgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crestinfosystems_jinay.wishlistapp.utils.ColorUtils
import com.crestinfosystems_jinay.wishlistapp.utils.ComposeUtils


@Composable
fun AppBar(title: String, onBackNavClickerd: () -> Unit = {}) {
    val navigationIcon: (@Composable
        () -> Unit) = {
        IconButton(onClick = {
            onBackNavClickerd()
        }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = ColorUtils.textColor
            )
        }
    }
    if (!title.contains("Wish List")) {
        TopAppBar(
            navigationIcon = {
                             navigationIcon()
            },
            title = {
                androidx.compose.material3.Text(
                    text = title,
                    color = ColorUtils.textColor,
                    fontSize = ComposeUtils.modifyTextSizeBasedOnScreenSize(
                        baseSize = 24F
                    ).sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            elevation = 4.dp,
            backgroundColor = ColorUtils.primaryBackGroundColor

        )
    } else {
        TopAppBar(
            title = {
                androidx.compose.material3.Text(
                    text = title,
                    color = ColorUtils.textColor,
                    fontSize = ComposeUtils.modifyTextSizeBasedOnScreenSize(
                        baseSize = 24F
                    ).sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            elevation = 4.dp,
            backgroundColor = ColorUtils.primaryBackGroundColor

        )
    }


}