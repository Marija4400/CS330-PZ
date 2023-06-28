package com.example.cs330_pz05.presentation.recept_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cs330_pz05.domain.model.Recept


@Composable
fun ReceptListItem(
    recept:Recept,
    onItemClick: (Recept) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(recept) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = recept.image,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .width(250.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${recept.naziv} ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.clickable { expanded =!expanded }
                )
                Text(
                    text = "Vreme pripreme: ${recept.vreme_pripreme}", color = Color.Gray,
                    modifier = Modifier.padding(4.dp)
                )
                if (expanded) {

                    Text(
                        text = "Tezina pripreme: ${recept.difficulty}",
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "Kalorijska vrednost: ${recept.kalorije}",
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.titleMedium
                    )


                }



            }

        }


    }
}