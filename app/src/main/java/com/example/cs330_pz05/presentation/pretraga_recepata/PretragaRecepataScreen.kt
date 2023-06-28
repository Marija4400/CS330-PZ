package com.example.cs330_pz05.presentation.pretraga_recepata

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cs330_pz05.presentation.Screen
import com.example.cs330_pz05.presentation.recept_list.ReceptListViewModel
import com.example.cs330_pz05.presentation.recept_list.components.ReceptListItem

@Composable
fun PretragaRecepataScreen (
    navController: NavController,
    viewModel: PretragaRecepataViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Log.d("Pretraga ekran",state.recepti.toString())
    Box(modifier = Modifier.fillMaxSize()){


            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.recepti){recept ->
                    ReceptListItem(
                        recept =recept,
                        onItemClick = {
                            navController.navigate(Screen.ReceptDetailScreen.route + "/${recept.id}")
                        })

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
                    .align(Alignment.Center)


            )
        }

        if(state.isLoading){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }


    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        FloatingActionButton(
            onClick = { navController.navigate(Screen.CheckedSastojakScreen.route)},
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd) // Postavljanje na donji desni ugao
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Dodaj"
            )
        }
    }
}