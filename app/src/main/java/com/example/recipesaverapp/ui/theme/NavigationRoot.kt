package com.example.recipesaverapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.recipesaverapp.DisplayScreen

@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(DisplayScreenKey)
    NavDisplay(
        modifier = Modifier, backStack = backStack,
        entryProvider = { Key ->
            when (Key) {
                is DisplayScreenKey -> {
                    NavEntry(Key) {
                        DisplayScreen()
                    }
                }

                is InputScreenKey -> {
                    NavEntry(Key) {
                        InputScreen()
                    }
                }

                else -> throw IllegalArgumentException("Unknown key: $Key")
            }
        }
    )
}