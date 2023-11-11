package com.azhar.composequotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azhar.composequotes.data.Quotes
import com.azhar.composequotes.data.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

/*
* @created 11/11/2023 -11:20 PM
* @project ComposeQuotes
* @author  azhar
*/

class QuotesViewModel(private val quotesRepository: QuotesRepository):ViewModel() {
    fun getAllQuotes(): Flow<List<Quotes>> {
       return quotesRepository.quotes
    }

}


class QuotesViewModelFactory(private val repository: QuotesRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesViewModel::class.java)){
            return QuotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}