package com.example.recipesaverapp.ui.theme

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object DisplayScreenKey : NavKey

@Serializable
data object InputScreenKey : NavKey

@Serializable
data class RecipeData(
    val name: String,
    val ingredients: String,
    val process: String,
)