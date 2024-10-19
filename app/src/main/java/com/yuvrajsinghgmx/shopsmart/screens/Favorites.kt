package com.yuvrajsinghgmx.shopsmart.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.yuvrajsinghgmx.shopsmart.viewmodel.ShoppingListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(viewModel: ShoppingListViewModel = hiltViewModel(), navController: NavController) {
    val items = viewModel.items.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Favorites",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items.value.size) { index ->
                    if(items.value[index].isFavorite) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .border(2.dp, color = MaterialTheme.colorScheme.outline, RoundedCornerShape(16.dp)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp)
                            ) {
                                if (items.value[index].imageUrl != null) {
                                    AsyncImage(
                                        model = items.value[index].imageUrl,
                                        contentDescription = items.value[index].name,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(70.dp, 70.dp)
                                            .clip(CircleShape)
                                            .padding(end = 1.dp)
                                            .border(
                                                BorderStroke(1.dp, Color(0xFF332D25)),
                                                CircleShape
                                            )
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = items.value[index].name,
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "₹${items.value[index].amount}",
                                        style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}