package com.carpisoft.guau.commons.ui.screens.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carpisoft.guau.R
import com.carpisoft.guau.ui.theme.BackgroundHead
import com.carpisoft.guau.ui.theme.GuauTheme

@Composable
fun HeadScaffold(
    title: String,
    showNavigation: Boolean,
    titleFontSize:TextUnit,
    iconSize:Dp,
    appBarHeight: Dp,
    dropdownMenuWidth:Dp,
    signOffOnClick:()->Unit,
    onBackOnClick:()->Unit
) {
    var openMenu by rememberSaveable { mutableStateOf(false) }
    MyTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = titleFontSize)

        },
        appBarHeight = appBarHeight,
        backgroundColor = BackgroundHead,
        navigationIcon = if (showNavigation) {
            {
                IconButton(onClick = onBackOnClick) {
                    Icon(
                        modifier = Modifier.size(iconSize),
                        imageVector = Icons.Default.ArrowBackIos,
                        contentDescription = "",
                    )
                }
            }
        } else {
            null
        },
        actions = {
            IconButton(onClick = { openMenu = !openMenu }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = ""
                )
            }
            DropdownMenu(
                modifier = Modifier.width(dropdownMenuWidth),
                expanded = openMenu,
                onDismissRequest = { openMenu = false }) {

                DropdownMenuItem(onClick = {
                    openMenu = false


                }, text = {
                    Row() {
                        Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = stringResource(id = R.string.my_profile))
                    }

                })
                Divider()
                DropdownMenuItem(onClick = {
                    openMenu = false
                    signOffOnClick()
                }, text = {
                    Row() {
                        Icon(imageVector = Icons.Filled.Logout, contentDescription = "")
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = stringResource(id = R.string.sign_off))
                    }
                })
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X, widthDp = 320, heightDp = 534)
@Composable
private fun PreviewSmall() {
    GuauTheme {
        Scaffold(
            topBar = {
                HeadScaffold(
                    title = "Test",
                    showNavigation = true,
                    titleFontSize = 16.sp,
                    iconSize = 20.dp,
                    appBarHeight = 40.dp,
                    dropdownMenuWidth = 200.dp,
                    signOffOnClick = {},
                    onBackOnClick = {}
                )
            }){ contentPadding ->
            Box(modifier = Modifier.padding(contentPadding)) {

            }
        }
    }
}