package com.azhar.composequotes.widgets

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun CardContents() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Image(imageVector = Icons.Outlined.Share, contentDescription = "")
                Text(text = "Copy")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Image(imageVector = Icons.Outlined.Share, contentDescription = "")
                Text(text = "Save")

            }
            TextButton(onClick = { /*TODO*/ }) {
                Image(imageVector = Icons.Outlined.Share, contentDescription = "")
                Text(text = "Share")


            }
            TextButton(onClick = { /*TODO*/ }) {
                Image(imageVector = Icons.Outlined.Delete, contentDescription = "")
                Text(text = "Delete")


            }
        }

}