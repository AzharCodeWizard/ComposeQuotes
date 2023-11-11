package com.azhar.composequotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* @created 11/11/2023 -11:04 PM
* @project ComposeQuotes
* @author  azhar
*/
@Entity(tableName = "allcategories")
data class Quotes(@PrimaryKey @ColumnInfo(name = "category")val category:String, val nos:Int, val clickcount:Int, val quote:String,
                  val noofquotes:Int)
