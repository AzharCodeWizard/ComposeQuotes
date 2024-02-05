package com.azhar.composequotes.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

/*
* @created 11/11/2023 -11:04 PM
* @project ComposeQuotes
* @author  azhar
*/
@Parcelize
@Immutable
@Entity(tableName = "allcategories")
data class Quotes(
    @ColumnInfo(name = "category")
    var category: String,
    @ColumnInfo(name = "quote")
    var quote: String? = null,
    @PrimaryKey
    @ColumnInfo(name = "quoteId")
    var quoteId: Int
) : Parcelable
