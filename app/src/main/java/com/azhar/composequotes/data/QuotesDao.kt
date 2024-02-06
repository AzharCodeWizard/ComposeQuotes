package com.azhar.composequotes.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/*
* @created 11/11/2023 -11:08 PM
* @project ComposeQuotes
* @author  azhar
*/
@Dao
interface QuotesDao {
    @Query("SELECT * from allcategories where quote is not null  ORDER By quoteId ASC")
    fun getAllQuotes(): Flow<List<Quotes>>
}