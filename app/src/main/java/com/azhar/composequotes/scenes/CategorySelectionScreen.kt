package com.azhar.composequotes.scenes

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import com.azhar.composequotes.QuotesViewModel
import com.azhar.composequotes.QuotesViewModelFactory
import com.azhar.composequotes.Screens
import com.azhar.composequotes.data.QuotesDao
import com.azhar.composequotes.data.repository.QuotesRepository

@Composable
    @Preview(showBackground = true, showSystemUi = true)
    fun  CategorySelectionScreen(controller: NavHostController? = null, applicationContext: Context) {

          Column(Modifier.background(Color.Blue), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
              Text(text = "CategoryScreen", color = Color.White)
              Button(onClick = { controller?.navigate(Screens.DETAILS.name) }){
                  Text(text = "I am text button", color = Color.White)
              }
          }

    }