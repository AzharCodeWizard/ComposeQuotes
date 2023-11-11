package com.azhar.composequotes.scenes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@Composable
    @Preview(showBackground = true, showSystemUi = true)
     fun DetailsScreen(controller: NavHostController?=null) {
        Column (Modifier.background(Color.White)){
            Text(text = "DetailsScreen", color = Color.Black)
        }
    }