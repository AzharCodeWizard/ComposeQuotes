package com.azhar.composequotes.scenes

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
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
import com.azhar.composequotes.data.Quotes
import com.azhar.composequotes.widgets.CardContents
import kotlin.random.Random

/**
 * @created 11/11/2023 -8:09 PM
 * @project ComposeQuotes
 * @author  azhar
 */
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategorySelectionScreen(
    controller: NavHostController? = null, viewModel: QuotesViewModel? = null
) {
    val searchString = viewModel?.onChangeTextObserver()?.observeAsState()
    val listQuotes = viewModel?.getAllQuotes()?.collectAsState(initial = arrayListOf())
    val filteredList =listQuotes?.value?.filter { (it.quote?:"").contains(searchString?.value?:"") }?.toMutableStateList()

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.White),
        content = {
            if (searchString?.value?.isNotBlank() == true){
                items(filteredList?: arrayListOf()) {
                    QuotesItem(it = it, controller = controller)
                }
            }else{
             listQuotes?.value?.let { quotes ->
                items(quotes) {
                    QuotesItem(it = it, controller = controller)
                }
            }
            }
        })

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun QuotesItem(
    it: Quotes? = null, controller: NavHostController? = null
) {
    val rememberColor = rememberSaveable(stateSaver = ColorSaver) {
        mutableStateOf(randomColor())
    }
    val expansionCard = rememberSaveable {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .clickable {
//            expansionCard.value = expansionCard.value.not()
        }
        .padding(8.dp)
        .fillMaxWidth()
        .height(180.dp),
        colors = CardDefaults.cardColors(containerColor = rememberColor.value),
        elevation = CardDefaults.cardElevation(5.dp)) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = it?.quote.toString(),
                color = Color.White,
                lineHeight = 35.sp,
                textAlign = TextAlign.Center,
                fontSize = 30.sp, maxLines = 5,
                letterSpacing = TextUnit(1f, TextUnitType.Sp),
                fontFamily = FontFamily.Serif,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
            if (expansionCard.value) {
                CardContents()
            }


        }

    }

}

fun randomColor() = Color(
    Random.nextInt(150, 200), Random.nextInt(150, 200), Random.nextInt(100, 200)
)

object ColorSaver : Saver<Color, Bundle> {
    override fun restore(value: Bundle): Color? {
        val colorLong = value.getString("color")?.toULong()
        return colorLong?.let { Color(it) }
    }

    override fun SaverScope.save(value: Color): Bundle {
        val bundle = Bundle()
        bundle.putString("color", value.value.toString())
        return bundle
    }
}