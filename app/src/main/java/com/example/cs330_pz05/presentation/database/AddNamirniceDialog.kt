package com.example.cs330_pz05.presentation.database

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cs330_pz05.data.database.NamirniceEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNamirniceDialog(
    state: NamirniceState,
    onEvent: (NamirniceEvent) -> Unit,
    modifier: Modifier = Modifier

) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(NamirniceEvent.HideDialog)
        },
        title = { Text(text = "Dodaj namirnice") },
        text = {
            Column {
                TextField(
                    value = state.tipProdavnice,
                    onValueChange = {
                        onEvent(NamirniceEvent.SetTipProdavnice(it))
                    },
                    label = { Text(text = "Tip prodavnice") }
                )
                TextField(
                    value = state.naziv,
                    onValueChange = {
                        onEvent(NamirniceEvent.SetNaziv(it))
                    },
                    label = { Text(text = "Naziv proizvoda") }
                )
                TextField(
                    value = state.kolicina,
                    onValueChange = {
                        onEvent(NamirniceEvent.SetKolicina(it))
                    },
                    label = { Text(text = "Količina") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onEvent(NamirniceEvent.SaveNamirnice)
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors()
            ) {
                Text(text = "Save")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onEvent(NamirniceEvent.HideDialog)
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(text = "Cancel")
            }
        }
    )
}
