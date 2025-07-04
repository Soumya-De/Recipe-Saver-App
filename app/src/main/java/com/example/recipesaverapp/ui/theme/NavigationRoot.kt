package com.example.recipesaverapp.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.recipesaverapp.DisplayScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationRoot() {
    val backStack = rememberNavBackStack(DisplayScreenKey)
    val recipeList = remember { mutableStateListOf<RecipeData>() }

    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val selectedRecipe = remember { mutableStateOf<RecipeData?>(null) }


    NavDisplay(
        modifier = Modifier,
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is DisplayScreenKey -> {
                    NavEntry(key) {
                        DisplayScreen(
                            recipes = recipeList,
                            onAdd = { backStack.add(InputScreenKey) },
                            onTap = { recipe ->
                                selectedRecipe.value = recipe
                                isSheetOpen = true
                            },
                            sheetState = sheetState,
                            isSheetOpen = isSheetOpen,
                            onDismissSheet = { isSheetOpen = false },
                            selectedRecipe = selectedRecipe
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
