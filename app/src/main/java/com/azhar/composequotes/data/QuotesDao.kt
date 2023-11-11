package com.azhar.composequotes.data

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*
* @created 11/11/2023 -11:08 PM
* @project ComposeQuotes
* @author  azhar
*/

interface QuotesDao {
    @Query("SELECT * from allcategories ORDER By noofquotes ASC")
    fun getAllQuotes(): Flow<List<Quotes>>
}