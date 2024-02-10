package com.azhar.composequotes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.azhar.composequotes.data.QuotesRoomDatabase
import com.azhar.composequotes.data.repository.QuotesRepository
import com.azhar.composequotes.scenes.CategorySelectionScreen
import com.azhar.composequotes.scenes.DetailsScreen
import com.azhar.composequotes.ui.theme.ComposeQuotesTheme
import kotlinx.coroutines.launch

/*
* @created 11/26/2023 -4:10 PM
* @project ComposeQuotes
* @author  azhar
*/
class MainActivity : ComponentActivity() {
    private val viewModel: QuotesViewModel by viewModels {
        QuotesViewModelFactory(
            QuotesRepository(
                QuotesRoomDatabase.getDataBase(this@MainActivity).quotesDao()
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            var showBottomSheet by remember { mutableStateOf(false) }
            val isDarkThemEnabled =
                rememberSaveable {
                    mutableStateOf(false)
                }

            if (showBottomSheet) {
                ModalBottomSheet(modifier = Modifier.height(height = 300.dp),
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Hide bottom sheet")
                    }
                }
            }
            MaterialTheme(colorScheme = if (isDarkThemEnabled.value) darkColorScheme() else lightColorScheme(),
                content = {
                    ComposeQuotesTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background,
                        ) {
                            Scaffold(topBar = {

                            }, floatingActionButton = {
                                FloatingActionButton(shape = CircleShape, onClick = {
                                    showBottomSheet=showBottomSheet.not()
                                    isDarkThemEnabled.value = !isDarkThemEnabled.value
                                }) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                                }
                            }) {
                                Surface(
                                    modifier = Modifier
                                        .padding(top = it.calculateTopPadding())
                                        .fillMaxSize()
                                ) {
                                    HomeScreen()

                                }


                            }

                        }
                    }
                })
        }


    }

    @Composable
    private fun HomeScreen() {
        val controller = rememberNavController()
        NavHost(navController = controller, startDestination = Screens.CATEGORY.name) {
            composable(route = Screens.CATEGORY.name) {
                CategorySelectionScreen(controller, viewModel)
            }
            composable(route = Screens.DETAILS.name.plus("/{quote}")) {
                DetailsScreen(controller)
            }
        }

    }


}


