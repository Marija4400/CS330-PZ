package com.example.cs330_pz05.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.cs330_pz05.data.database.NamirniceDatabase
import com.example.cs330_pz05.data.database.NamirniceEvent
import com.example.cs330_pz05.presentation.checked_sastojak.components.CheckedSastojakScreen
import com.example.cs330_pz05.presentation.database.NamirniceScreen
import com.example.cs330_pz05.presentation.database.NamirniceState
import com.example.cs330_pz05.presentation.database.NamirniceViewModel
import com.example.cs330_pz05.presentation.pretraga_recepata.PretragaRecepataScreen
import com.example.cs330_pz05.presentation.recept_detail.ReceptDetailScreen
import com.example.cs330_pz05.presentation.recept_list.ReceptListScreen
import com.example.cs330_pz05.presentation.theme.CS330PZ05Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            NamirniceDatabase::class.java,
            "namirnice.db"
        ).build()
    }
    private val viewModel by viewModels<NamirniceViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel>create(modelClass: Class<T>): T {
                    return NamirniceViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330PZ05Theme {
                val state by viewModel.state.collectAsState()
                Surface(
                    color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ReceptListScreen.route
                    ){
                        composable(
                            route = Screen.ReceptListScreen.route
                        ){
                            ReceptListScreen(navController)
                        }
                        composable(
                            route = Screen.ReceptDetailScreen.route + "/{receptId}"
                        ){
                            ReceptDetailScreen(navController = navController)
                        }
                        composable(
                            route = Screen.CheckedSastojakScreen.route
                        ){
                            CheckedSastojakScreen(navController = navController)
                        }
                        composable(
                            route = Screen.NamirniceScreen.route
                        ){
                                NamirniceScreen(navController = navController, onEvent = viewModel::onEvent, state = state)
                        }
                        composable(
                            route = Screen.PretragaScreen.route
                        ){
                            PretragaRecepataScreen(navController = navController)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CS330PZ05Theme {
        Greeting("Android")
    }
}