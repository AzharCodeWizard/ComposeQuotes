package com.azhar.composequotes.data.repository

import com.azhar.composequotes.data.Quotes
import com.azhar.composequotes.data.QuotesDao
import kotlinx.coroutines.flow.Flow

/*
* @created 11/11/2023 -11:16 PM
* @project ComposeQuotes
* @author  azhar
*/

class QuotesRepository(private val quotesDao: QuotesDao) {


    val quotes: Flow<List<Quotes>>
        get() {
            return quotesDao.getAllQuotes()
        }


}