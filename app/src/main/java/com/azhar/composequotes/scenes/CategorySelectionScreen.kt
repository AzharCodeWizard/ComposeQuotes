package com.azhar.composequotes.scenes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.azhar.composequotes.QuotesViewModel
import com.azhar.composequotes.Screens
import com.azhar.composequotes.data.Quotes
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategorySelectionScreen(
    controller: NavHostController? = null,
    viewModel: QuotesViewModel? = null
) {
    val collectAsState = viewModel?.getAllQuotes()?.collectAsState(initial = arrayListOf())
    var selectedQuotes by remember { mutableStateOf<Quotes?>(null) }
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.White),
        content = {
            this.item {
                collectAsState?.value?.forEach {
                    Card(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f)
                        .height(150.dp)
                        .fillParentMaxWidth(1f),
                        colors = CardDefaults.cardColors(containerColor = randomColor()),
                        elevation = CardDefaults.cardElevation(5.dp),
                        onClick = onItemClick(controller =controller, it =it, onclick ={selectedQuotes=it})
                    ) {
                        Text(
                            text = it.quote.toString(),
                            color = Color.White,
                            lineHeight = 35.sp,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp, letterSpacing = TextUnit(1f, TextUnitType.Sp),
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .padding(8.dp)
                        )
                    }
                }
            }
        })
}

@Composable
private fun onItemClick(
    controller: NavHostController?,
    it: Quotes, onclick: () -> Unit
): () -> Unit = { controller?.navigate(Screens.DETAILS.name.plus("/${it.quote}")) }

fun randomColor() = Color(
    Random.nextInt(100, 200),
    Random.nextInt(100, 200),
    Random.nextInt(100, 200)
)