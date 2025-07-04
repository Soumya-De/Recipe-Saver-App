package com.example.recipesaverapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InputScreen(onSave: (String, String, String) -> Unit, onCancel: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") }
    var preparationSteps by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.padding(24.dp).fillMaxWidth().verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add New Recipe",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50),
            modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.height(200.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text("Recipe Name") },
            placeholder = { Text("Enter Recipe Name") },
            leadingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = ingredients,
            onValueChange = {ingredients = it},
            label = { Text("Ingredients") },
            placeholder = { Text("Enter List Of Ingredients") },
            leadingIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            textStyle = TextStyle(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.height(12.dp))


        OutlinedTextField(
            value = preparationSteps,
            onValueChange = {preparationSteps = it},
            label = { Text("Preparation Steps") },
            placeholder = { Text("Describe Each Step In Detail") },
            leadingIcon = { Icon(imageVector = Icons.Default.List, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            textStyle = TextStyle(fontSize = 16.sp),
        )

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { onCancel() },
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cancel", style = TextStyle(fontSize = 16.sp))
            }

            Button(
                onClick = { onSave(name, ingredients, preparationSteps) },
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50),
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save", style = TextStyle(fontSize = 16.sp))
            }
        }
    }
}