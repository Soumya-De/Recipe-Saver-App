package com.example.recipesaverapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recipesaverapp.ui.theme.NavigationRoot
import com.example.recipesaverapp.ui.theme.RecipeSaverAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeSaverAppTheme {
                NavigationRoot()
            }
        }
    }
}
