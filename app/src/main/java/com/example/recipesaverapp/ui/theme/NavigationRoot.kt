package com.example.recipesaverapp.ui.theme

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.recipesaverapp.DisplayScreen

@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(DisplayScreenKey)
    val recipeList = remember { mutableStateListOf<RecipeData>() }

    NavDisplay(
        modifier = Modifier,
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is DisplayScreenKey -> {
                    NavEntry(key) {
                        DisplayScreen(
                            recipes = recipeList,
                            onAdd = { backStack.add(InputScreenKey) }
                        )
                    }
                }

                is InputScreenKey -> {
                    NavEntry(key) {
                        InputScreen(
                            onSave = { name, ingredients, process ->
                                recipeList.add(RecipeData(name, ingredients, process))
                                backStack.removeAt(backStack.lastIndex)
                            },
                            onCancel = { backStack.removeAt(backStack.lastIndex) }
                        )
                    }
                }

                else -> throw IllegalArgumentException("Unknown key: $key")
            }
        }
    )
}
