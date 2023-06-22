package com.carpisoft.guau.commons.ui.screens.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextQuestionWithLink(
    modifier: Modifier,
    text: String,
    link: String,
    onClickLink: () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        Text(text = text)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = link,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                onClickLink()
            })
    }
}

@Preview
@Composable
private fun Preview() {
    TextQuestionWithLink(
        modifier = Modifier.fillMaxWidth(),
        text = "No tienes una cuenta?",
        link = "Registrate"
    ) {

    }
}