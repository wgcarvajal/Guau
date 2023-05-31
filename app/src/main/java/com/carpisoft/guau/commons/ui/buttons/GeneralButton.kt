package com.carpisoft.guau.commons.ui.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GeneralButton(modifier: Modifier, label: String, enabled: Boolean, onClick: () -> Unit) {
    Button(modifier = modifier, onClick = onClick, enabled = enabled) {
        Text(text = label)
    }
}

@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X)
@Composable
private fun Preview() {
    GeneralButton(modifier = Modifier, label = "Login",enabled = true) {
    }
}