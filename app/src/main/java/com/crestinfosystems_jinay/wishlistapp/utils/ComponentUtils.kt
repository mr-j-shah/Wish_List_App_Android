package com.crestinfosystems_jinay.wishlistapp.utils

// File: ComposeUtils.kt
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ComposeUtils {

    @Composable
    fun modifyTextSizeBasedOnScreenSize(
        baseSize: Float,
        modifier: Modifier = Modifier
    ): Int {
        val density = LocalDensity.current.density
        val screenWidthDp =LocalConfiguration.current.screenWidthDp

        val scaledSize = baseSize * (screenWidthDp / 360f) // Adjust the factor as needed

        return scaledSize.toInt()
    }

    @Composable
    fun modifyDimensionsBasedOnScreenSize(
        baseWidth: Dp = LocalConfiguration.current.screenHeightDp.dp,
        baseHeight: Dp = LocalConfiguration.current.screenHeightDp.dp,
        modifier: Modifier = Modifier
    ): Modifier {
        val density = LocalDensity.current.density
        val screenWidthDp = LocalConfiguration.current.screenWidthDp
        val screenHeightDp = LocalConfiguration.current.screenHeightDp

        val scaledWidth = baseWidth * (screenWidthDp / 360f) // Adjust the factor as needed
        val scaledHeight = baseHeight * (screenHeightDp / 640f) // Adjust the factor as needed

        return modifier.then(
            Modifier.width(scaledWidth).then(
                Modifier.height(scaledHeight)
            )
        )
    }
}