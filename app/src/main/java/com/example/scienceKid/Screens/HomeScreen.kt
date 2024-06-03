package com.example.scienceKid.Screens


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scienceKid.QRCodeScanner


data class UnitItem(val name: String, val id: Int)
data class LectureItem(val name: String, val id: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(qrCodeScanner: QRCodeScanner?) {
    val sampleUnits = listOf(
        UnitItem("Unit 1", 1),
        UnitItem("Unit 2", 2),
        UnitItem("Unit 3", 3),
        UnitItem("Unit 4", 4),
        UnitItem("Unit 5", 5),
        UnitItem("Unit 6", 6),
        UnitItem("Unit 7", 7),
        UnitItem("Unit 8", 8),
        UnitItem("Unit 9", 9),
        UnitItem("Unit 10", 10)
    )

    val sampleLectures = listOf(
        LectureItem("Lecture 1", 1),
        LectureItem("Lecture 2", 2),
        LectureItem("Lecture 3", 3),
        LectureItem("Lecture 4", 4),
        LectureItem("Lecture 5", 5),
    )


    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                if (qrCodeScanner != null) {
                    QRCodeBar(qrCodeScanner = qrCodeScanner)
                }
            },
            modifier = Modifier.fillMaxSize()
        ) {
                innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                        .padding(3.dp)
                ) {
                    items(sampleUnits) {
                            unit ->
                        var expandedState by remember{ mutableStateOf(false)}
                        val rotationState by animateFloatAsState(targetValue = if(expandedState) 180f else 0f)
                        OutlinedCard(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                            ),
                            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primaryContainer),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .animateContentSize(
                                    animationSpec = tween(
                                        durationMillis = 300,
                                        easing = LinearOutSlowInEasing
                                    )
                                ),
                            shape = MaterialTheme.shapes.medium,
                            onClick = {
                                expandedState = !expandedState
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp),
                            ){
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Text(
                                        modifier = Modifier.weight(6f),
                                        text = unit.name,
                                        fontSize =  MaterialTheme.typography.displayMedium.fontSize,
                                        fontWeight =  FontWeight.Bold,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    IconButton(
                                        modifier = Modifier
                                            .weight(1f)
                                            .alpha(0.6f)
                                            .rotate(rotationState),
                                        onClick = {
                                            expandedState = !expandedState
                                        }) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = "Drop-Down Arrow"
                                        )
                                    }
                                }
                                if(expandedState){
                                    Text(
                                        text = "This is the content of the unit",
                                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                        fontWeight = FontWeight.Normal,
                                        maxLines = 3,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(qrCodeScanner = null);
}
