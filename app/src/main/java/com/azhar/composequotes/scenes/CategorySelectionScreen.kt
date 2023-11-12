package com.azhar.composequotes.scenes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.azhar.composequotes.QuotesViewModel
import com.azhar.composequotes.Screens
import com.azhar.composequotes.data.Quotes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategorySelectionScreen(
    controller: NavHostController? = null,
    viewmodel: QuotesViewModel? = null
) {
    val collectAsState = viewmodel?.getAllQuotes()?.collectAsState(initial = arrayListOf(Quotes("Health","Where There is love there is trust",1)))

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(8.dp),
        content = {

            this.item {
                collectAsState?.value?.forEach {
                    Card(modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(0.dp, 10.dp), onClick = {
                        //
//                        controller?.navigate(Screens.DETAILS.name, navArgument("Quotes") {
//                            type = NavType.ParcelableType(Quotes::class.java)
//                        })
                            controller?.navigate(Screens.DETAILS.name.plus("/{quote}"))
                    }) {
                        Text(
                            text = it.quote.toString(),
                            color = Color.White,
                            modifier = Modifier.padding(10.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        })
//
//    Column(
//        Modifier.background(Color.Blue),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "CategoryScreen", color = Color.White)
//        Button(onClick = { controller?.navigate(Screens.DETAILS.name) }) {
//            Text(text = "I am text button", color = Color.White)
//        }
//    }

}