package com.example.scienceKid.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilledCard(title: String, onButtonClick: () -> Unit = {}) { // `onButtonClick` are un comportament implicit gol
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 150.dp) // Am mărit înălțimea pentru a face loc butonului
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Distribuim spațiul vertical între elemente
        ) {
            Text(
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.sp), // Dimensiune mai mică
                maxLines = 2, // Limitează la două linii
                overflow = TextOverflow.Ellipsis // Trunchiază textul dacă este prea lung
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onButtonClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Start")
            }
        }
    }
}

@Composable
@Preview
fun FilledCardPreview() {
    FilledCard(title = "This is a very long title that might need to be truncated", onButtonClick = {})
}
