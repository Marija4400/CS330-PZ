package com.example.cs330_pz05.presentation.database

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pz05.data.database.NamirniceEvent
import com.example.cs330_pz05.data.database.SortType
import com.example.cs330_pz05.presentation.checked_sastojak.CheckedSastojakViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NamirniceScreen (
    navController: NavController,
    state: NamirniceState,
    viewModel: NamirniceViewModel = hiltViewModel(),
    onEvent: (NamirniceEvent) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(NamirniceEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add sastojak"
                )
            }
        },
    ) { _ ->
        if(state.isAddingNamirnice) {
            AddNamirniceDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(NamirniceEvent.SortNamirnice(sortType))
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(NamirniceEvent.SortNamirnice(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.namirnice) { namirnice ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Tip prodavnice: ${namirnice.tipProdavnice}",
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Naziv: ${namirnice.naziv}", fontSize = 20.sp
                        )

                        Text(
                            text = "Kolicina: ${namirnice.kolicina}", fontSize = 15.sp
                        )
                    }
                    IconButton(onClick = {
                        onEvent(NamirniceEvent.DeleteNamirnice(namirnice))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete "
                        )
                    }
                }
            }
        }
    }
}