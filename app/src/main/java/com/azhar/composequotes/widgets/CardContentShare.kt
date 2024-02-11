package com.azhar.composequotes.widgets

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azhar.composequotes.R
import com.azhar.composequotes.ui.theme.colorBlack
import com.azhar.composequotes.ui.theme.colorWhite

@Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun CardContents() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight().background(color = colorBlack),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.share), contentDescription = "", modifier = Modifier.width(24.dp).height(24.dp))
                Spacer(modifier=Modifier.padding(1.dp))
                Text(text = "Copy", color = colorWhite)
            }
            TextButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.download), contentDescription = "",modifier = Modifier.width(24.dp).height(24.dp))
                Text(text = "Save", color = colorWhite)

            }
            TextButton(onClick = { /*TODO*/ }) {
                Image(painter =  painterResource(id = android.R.drawable.ic_menu_share), contentDescription = "",modifier = Modifier.width(24.dp).height(24.dp))
                Text(text = "Share", color = colorWhite)


            }
            TextButton(onClick = { /*TODO*/ }) {
                Image(painter =  painterResource(id = android.R.drawable.ic_delete), contentDescription = "",modifier = Modifier.width(24.dp).height(24.dp))
                Text(text = "Delete", color = colorWhite)


            }
        }

}