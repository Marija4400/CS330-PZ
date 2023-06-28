package com.example.cs330_pz05.presentation.recept_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cs330_pz05.domain.model.ReceptDetail


@Composable
fun ReceptDetailScreen(
    navController: NavController,
    viewModel: ReceptDetailViewModel = hiltViewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()){
        state.recept?.let{ recept ->
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
                ){
                item {

                    Card(
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            IconButton(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .scale(1.5f),
                                onClick = { navController.popBackStack()}) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            Text(text = "Recept",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.align(Alignment.Center))

                        }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally

                            ) {
                                AsyncImage(
                                    model =recept.image ,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(280.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Text(text = "${recept.naziv}", style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier.padding(top = 4.dp),
                                    fontWeight = FontWeight.Bold)
                                Text(
                                    text = "Tezina pripreme: ${recept.difficulty}", color = Color.Gray,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 30.dp, vertical = 8.dp)
                                ) {
                                    Icon(Icons.Default.Info, contentDescription = "Info")
                                    Text(
                                        text = "${recept.sastojci}",
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                            Card(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 5.dp
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f))
                                        .padding(16.dp)
                                        .clip(MaterialTheme.shapes.small)
                                        .align(Alignment.CenterHorizontally) // Postavljanje na sredinu horizontalno
                                ) {
                                    Text(
                                        text = "Kalorije: ${recept.kalorije}",
                                        color = MaterialTheme.colorScheme.onTertiary,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier
                                            .padding(bottom = 8.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                    Text(
                                        text = "Vreme pripreme: ${recept.vreme_pripreme}",
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onTertiary,
                                        modifier = Modifier.align(Alignment.CenterHorizontally) // Postavljanje na sredinu horizontalno
                                    )

                                }
                                //izgled kartice koja se otvara za nacin pripteme:
                                Card(
                                    elevation = CardDefaults.cardElevation(
                                        defaultElevation = 10.dp
                                    ),
                                    shape = MaterialTheme.shapes.medium,
                                    modifier = Modifier
                                        .padding(16.dp),
                                ) {
                                    recept?.let {
                                        Row(horizontalArrangement = Arrangement.Center,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(16.dp)
                                        ) {
                                            Text(
                                                text = "NACIN PRIPREME: \n${it.nacinPripreme}",

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


        if(state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)


            )
        }

        if(state.isLoading){
            CircularProgressIndicator()
        }


    }


