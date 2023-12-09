package com.azhar.composequotes.scenes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.azhar.composequotes.data.Quotes

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetailsScreen(controller: NavHostController? = null) {
    val quotes = controller?.currentBackStackEntry?.arguments?.getString("quote")
    Column(Modifier.background(Color.White)) {
        Text(modifier = Modifier.padding(20.dp), lineHeight = TextUnit(40f,TextUnitType.Sp), fontFamily = FontFamily.Serif,
            fontSize = 40.sp, text = quotes ?: "", color = Color.Black, letterSpacing = TextUnit(
                1f,
                TextUnitType.Sp
            )
        )
    }
    
}