package com.carpisoft.guau.initialsetup.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.carpisoft.guau.R
import com.carpisoft.guau.commons.ui.screens.loading.SimpleLoading
import com.carpisoft.guau.ui.theme.GuauTheme

@Composable
fun MyVetsScreen(
    myVetsViewModel: MyVetsViewModel,
    showNavigation: (Boolean) -> Unit,
    showFloatActionButton: (Boolean, () -> Unit) -> Unit,
    onSetTitle: (String) -> Unit
) {

    onSetTitle(stringResource(id = R.string.my_vets))
    showNavigation(true)
    val loading by myVetsViewModel.loading.collectAsState()
    val isEmpty by myVetsViewModel.isEmpty.collectAsState()
    val showError by myVetsViewModel.showError.collectAsState()

    ScreenPortrait(
        loading = loading,
        isEmpty = isEmpty,
        showError = showError,
        showFloatActionButton = showFloatActionButton
    )
    /*TwoButtonDialog(
        show = showError,
        message = ,
        confirmButtonText = ,
        cancelButtonText = ,
        confirmButton = { /*TODO*/ }) {
    }*/
}


@Composable
private fun ScreenPortrait(
    loading: Boolean, isEmpty: Boolean,
    showError: Boolean,
    showFloatActionButton: (Boolean, () -> Unit) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    if (loading) {
        SimpleLoading()
    } else if (isEmpty) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (message) = createRefs()
            showFloatActionButton(true) {
            }
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(
                    id = R.string.you_do_not_have_any_veterinary_created
                ),
                modifier = Modifier.constrainAs(message) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    } else if (!showError) {

    }
}

@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X, widthDp = 320, heightDp = 534)
@Composable
private fun PreviewSmall() {
    GuauTheme {
        ScreenPortrait(
            loading = false,
            isEmpty = true,
            showError = false,
            showFloatActionButton = { _, _ -> })
    }
}