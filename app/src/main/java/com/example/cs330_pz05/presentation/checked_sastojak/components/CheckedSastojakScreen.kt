package com.example.cs330_pz05.presentation.checked_sastojak.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.cs330_pz05.presentation.Screen
import com.example.cs330_pz05.presentation.checked_sastojak.CheckedSastojakViewModel
import com.example.cs330_pz05.presentation.pretraga_recepata.PretragaRecepataViewModel
import com.example.cs330_pz05.presentation.recept_list.ReceptListViewModel
import com.example.cs330_pz05.presentation.recept_list.components.ReceptListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckedSastojakScreen(
    navController: NavController,
    viewModel: CheckedSastojakViewModel = hiltViewModel(),
    vm: PretragaRecepataViewModel = hiltViewModel()
) {
    var oznaceni by remember { mutableStateOf(emptyList<String>()) }

    if (!vm.state.value.dialog){
        Box(modifier = Modifier.fillMaxSize()){


            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(vm.state.value.recepti){recept ->
                    ReceptListItem(
                        recept =recept,
                        onItemClick = {
                            navController.navigate(Screen.ReceptDetailScreen.route + "/${recept.id}")
                        })

                }
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
    else {

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()


        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .scale(1.5f),
                            onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.padding(16.dp))
                        }
                        Text(
                            text = "Namirnice:",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                }
                item {
                    Text(
                        text = "Odaberite namirnice koje trenutno imate: ",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR701qW7kYsYn-3ajKwoNm3wTp4py3dK8uXGA&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Meso"
                            Log.d("Ovde mozda radi", oznaceni.toString())
                        })
                    )
                    Text(text = "Meso", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Meso"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMTftFpHdeLB4uRQu_Qof5D2q_dq5Rci4-uw&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Krompir"
                        })

                    )
                    Text(text = "Krompir", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Krompir"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://online.idea.rs/images/products/310/310055454_1l.jpg?1677496526",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Sir"
                        })

                    )
                    Text(text = "Sir", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Sir"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGYHufgCKVsZJ66SWdyJ_KW_7OgeXG8HkSqQ&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Grasak"
                        })

                    )
                    Text(text = "Grasak", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Grasak"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3GhXSqsnMOE_6y-rotT8Pn0THd-80dpQ-dg&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Paradajz"
                        })

                    )
                    Text(text = "Paradajz", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Paradajz"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRpekqfe-qTeQLOtkSv2d9c6ScH5aqZFXhPw&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Kupus"
                        })

                    )
                    Text(text = "Kupus", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Kupus"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT6PFXZMWzSdlnypmm7Dlr7uGSHXajslrUWrA&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Zacin"
                        })

                    )
                    Text(text = "Zacin", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Zacin"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXRUCJPIWs8TbxYzRJmOeqYGy3BFnsOg22sw&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Nudle"
                        })

                    )
                    Text(text = "Nudle", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Nudle"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjik9X1-ubCLHnAJ6v64eM2cbEL51LMHmC7A&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Nudle"
                        })

                    )
                    Text(text = "Tunjevina", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Tunjevina"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMlmdrraUc6chuPcZ1VhUrSu3yPoX67CR2uQ&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Testenina"
                        })

                    )
                    Text(text = "Testenina", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Testenina"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN0eNZf5PGxXtiM2XuXcxAEy0N_FHzy91zHA&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Pirinac"
                        })

                    )
                    Text(text = "Pirinac", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Pirinac"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_3B2yNmTJ1RyNYt1KPfZETBgRQs56HcRCLg&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Ovsene pahuljice"
                        })

                    )
                    Text(text = "Ovsene pahuljice", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Ovsene pahuljice"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2RdJIX-x8i_5y_uyz3Ufx4TXUewLDWYUfTg&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Krem"
                        })

                    )
                    Text(text = "Krem", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Krem"
                        }
                    ))
                    Spacer(modifier = Modifier.height(8.dp))
                    FoodPreferenceItem(
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDnhMZcNvNz-DGQOfvsf8bQ51ri3kfRgw5gw&usqp=CAU",
                        modifier = Modifier.clickable(onClick = {
                            oznaceni = oznaceni + "Banane"
                        })

                    )
                    Text(text = "Banane", Modifier.clickable(
                        onClick = {
                            oznaceni = oznaceni + "Banane"
                        }
                    ))

                }
                item {
                    Button(
                        onClick = {
                            val zaPretragu = oznaceni.joinToString(",")
                            Log.d("Sastojci su ", zaPretragu)
                            Log.d("Sastojci su ", oznaceni.toString())
                            vm.setPtretraga(zaPretragu)
                            vm.getReceptiBySastojak(zaPretragu)
                            vm.setDialog()
                            //  vm.getReceptiBySastojak(zaPretragu)
                            // navController.popBackStack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(24.dp))
                    ) {
                        Text(text = "GENERISI RECEPT")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                item {
                    Button(
                        onClick = { navController.navigate(Screen.NamirniceScreen.route) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(24.dp))
                    ) {
                        Text(text = "KUPI NAMIRNICE")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }
    }
}

@Composable
fun CustomCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.CheckCircle,
    color: Color = MaterialTheme.colorScheme.primary,
    size: Dp = 45.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clickable { onCheckedChange(!checked) }
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (checked) color else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
            modifier = Modifier.size(size)
        )
    }
}

@Composable
fun FoodPreferenceItem(
    imageUrl: String? = null,
    modifier: Modifier) {

    val checkedState = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
    ) {
        CustomCheckbox(

            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier.padding(end = 8.dp)
        )

        if (imageUrl != null) {
            val painter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    crossfade(true)
                }
            )

            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            text = "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
    }

}