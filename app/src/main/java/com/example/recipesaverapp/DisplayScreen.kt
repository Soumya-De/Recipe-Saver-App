package com.example.recipesaverapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.State
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.recipesaverapp.ui.theme.RecipeData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayScreen(recipes: List<RecipeData>,
                  onAdd: () -> Unit,
                  onTap: (RecipeData) -> Unit,
                  isSheetOpen: Boolean,
                  onDismissSheet: () -> Unit,
                  sheetState: SheetState,
                  selectedRecipe: State<RecipeData?>)
{
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(24.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your Recipes",
                style = typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50),
                modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(top = 2.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(recipes) {i->
                    CardUI(index = i, onTap = onTap)
                }
            }
        }
        FloatingActionButton(
            onClick = {onAdd()},
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            shape = CircleShape,
            containerColor = Color(0xFF4CAF50),
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
        if (isSheetOpen) {
            BottomSheetUI(sheetState, selectedRecipe, onDismissSheet)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetUI(sheetState: SheetState, selectedRecipe: State<RecipeData?>, onDismissSheet: () -> Unit) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {onDismissSheet() },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            selectedRecipe.value?.let { index ->
                Text(
                    text = index.name,
                    modifier = Modifier.padding(16.dp),
                    style = typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Ingredients: ",
                        style = typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = index.ingredients,
                        style = typography.bodyMedium
                    )
                }
                Text(
                    text = "Process",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = index.process,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = typography.bodyMedium
                )
            }
        }
    }
}


@Composable
fun CardUI(index: RecipeData, onTap: (RecipeData) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp).clickable {onTap(index)},
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Text(
            text = index.name,
            modifier = Modifier.padding(16.dp),
            style = typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Ingredients: ",
                style = typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = index.ingredients,
                style = typography.bodyMedium
            )
        }
        Text(
            text = "Process:",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = index.process,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}