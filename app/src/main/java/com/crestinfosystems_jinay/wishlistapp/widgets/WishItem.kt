package com.crestinfosystems_jinay.wishlistapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.crestinfosystems_jinay.wishlistapp.DataModel.Wish
import com.crestinfosystems_jinay.wishlistapp.utils.ColorUtils

@Composable
fun wishItem(wish: Wish, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation = 10.dp,
        backgroundColor = if (!wish.isDone) ColorUtils.secondaryBakgroundColor else Color(0XFF198754)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = wish.title,
                fontWeight = FontWeight.ExtraBold,
                color = ColorUtils.textColor
            )
            Text(text = wish.desc, color = ColorUtils.subTextColor)
        }
    }
}