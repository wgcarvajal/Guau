package com.carpisoft.guau.commons.ui.screens.select

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSelectMenu(
    modifier: Modifier = Modifier
        .fillMaxWidth(),
    selectedItem: String,
    label: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    list: List<String>,
    onValueChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = onExpandedChange
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                value = selectedItem,
                label = { Text(text = label) },
                onValueChange = onValueChange,
                readOnly = true,
                trailingIcon = {
                    if (!expanded) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "",
                            modifier = Modifier.size(32.dp),
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = "",
                            modifier = Modifier
                                .rotate(180f)
                                .size(32.dp),
                            tint = Color.White
                        )
                    }
                },
            )

            DropdownMenu(
                modifier = Modifier
                    .exposedDropdownSize(),
                expanded = expanded,
                onDismissRequest = onDismissRequest
            ) {
                list.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item, color = Color.White, fontSize = 18.sp) },
                        onClick = {
                            onClick(item)
                        }
                    )
                }
            }
        }
    }
}

@Preview(name = "NEXUS_5", device = Devices.NEXUS_5X)
@Composable
private fun preview() {
    val context = LocalContext.current
    val coffeeDrinks = listOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    SimpleSelectMenu(
        selectedItem = selectedText,
        label = "Seleccione tipo de documento",
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        list = coffeeDrinks,
        onValueChange = {},
        onDismissRequest = { expanded = false },
        onClick = {
            selectedText = it
            expanded = false
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    )
}