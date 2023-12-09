package com.azhar.composequotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.azhar.composequotes.data.QuotesRoomDatabase
import com.azhar.composequotes.data.repository.QuotesRepository
import com.azhar.composequotes.scenes.CategorySelectionScreen
import com.azhar.composequotes.scenes.DetailsScreen
import com.azhar.composequotes.ui.theme.ComposeQuotesTheme

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black,
                ) {
                    HomeScreen()
                }
            }
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


