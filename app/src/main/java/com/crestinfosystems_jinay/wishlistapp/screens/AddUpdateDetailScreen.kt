package com.crestinfosystems_jinay.wishlistapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.crestinfosystems_jinay.wishlistapp.DataModel.Wish
import com.crestinfosystems_jinay.wishlistapp.utils.ColorUtils
import com.crestinfosystems_jinay.wishlistapp.utils.ComposeUtils
import com.crestinfosystems_jinay.wishlistapp.viewModel.WishViewModel
import com.crestinfosystems_jinay.wishlistapp.widgets.AppBar
import kotlinx.coroutines.launch

@Composable
fun AddUpdataeDetail(
    id: Long,
    navController: NavController,
    viewModel: WishViewModel
) {

    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scffoldState = rememberScaffoldState()
    if (id != 0L) {
        val wish =
            viewModel.getAwishById(id).collectAsState(initial = Wish(0L, title = "", desc = ""))
        viewModel.wishTitle = wish.value.title
        viewModel.wishDesc = wish.value.desc
    } else {
        viewModel.wishTitle = ""
        viewModel.wishDesc = ""
    }

    Scaffold(
        contentColor = ColorUtils.primaryBackGroundColor,
        topBar = {
            AppBar(
                title = if (id != 0L) {
                    "Update Wish"
                } else {
                    "Add Wish"
                }
            ) {
                navController.popBackStack()
            }
        },
        backgroundColor = ColorUtils.primaryBackGroundColor,
        scaffoldState = scffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {

            Spacer(modifier = Modifier.height(10.dp))
            textFields(viewModel.wishTitle, { changeVal ->
                viewModel.onWishTitleChange(changeVal)
            }, "Title")
            Spacer(modifier = Modifier.height(10.dp))
            textFields(viewModel.wishDesc, { changeVal ->
                viewModel.onWishDescChange(changeVal)
            }, "Description")
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (viewModel.wishTitle.isNotEmpty() && viewModel.wishDesc.isNotEmpty()) {
                        // Update Wish Function
                        if (id != 0L) {
                            //Update Wish
                            viewModel.updateWish(
                                wish = Wish(
                                    id = id,
                                    title = viewModel.wishTitle.trim(),
                                    desc = viewModel.wishDesc.trim(),
                                )
                            )
                            snackMessage.value = "Wish has been updated"
                        } else {
                            //Add Wish
                            viewModel.addWish(
                                Wish(
                                    title = viewModel.wishTitle.trim(),
                                    desc = viewModel.wishDesc.trim()
                                )
                            )
                            snackMessage.value = "Wish has been Created"
                        }
                    } else {
                        //
                        snackMessage.value = "Enter fields to create a wish"
                    }
                    scope.launch {
                        scffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .then(ComposeUtils.modifyDimensionsBasedOnScreenSize(baseHeight = 30.dp)),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ColorUtils.secondaryBakgroundColor)
            ) {
                Text(
                    text = if (id != 0L) {
                        "Update"
                    } else {
                        "Add"
                    },
                    color = ColorUtils.textColor,
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}


@Composable
fun textFields(value: String, onChange: (String) -> Unit, lable: String) {
    OutlinedTextField(value = value, onValueChange = {
        onChange(it)
    }, label = { Text(text = lable, color = ColorUtils.textColor) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = ColorUtils.textColor,
            focusedBorderColor = ColorUtils.secondaryBakgroundColor,
            focusedLabelColor = ColorUtils.textColor,
            unfocusedBorderColor = ColorUtils.secondaryBakgroundColor,
            unfocusedLabelColor = ColorUtils.textColor,
            cursorColor = ColorUtils.secondaryBakgroundColor
        ),
        shape = RoundedCornerShape(15.dp)
    )
}