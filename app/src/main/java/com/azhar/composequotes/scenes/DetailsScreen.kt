package com.azhar.composequotes.scenes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.azhar.composequotes.data.Quotes

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetailsScreen(controller: NavHostController? = null) {
    val quotes = controller?.previousBackStackEntry?.arguments?.getParcelable("Quotes") as? Quotes
    Column(Modifier.background(Color.White)) {
        Text(text = quotes?.quote ?: "", color = Color.Black)
    }
}