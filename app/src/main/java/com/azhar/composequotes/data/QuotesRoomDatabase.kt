package com.azhar.composequotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* @created 11/11/2023 -11:11 PM
* @project ComposeQuotes
* @author  azhar
*/
@Database(entities = [Quotes::class], version = 1, exportSchema = false)
abstract class QuotesRoomDatabase: RoomDatabase() {
    abstract fun quotesDao():QuotesDao

    companion object{
        @Volatile
        private var INSTANCE:QuotesRoomDatabase?=null
        fun getDataBase(context:Context): QuotesRoomDatabase {
            return INSTANCE?: synchronized(this){
                val instance= Room.databaseBuilder(context.applicationContext,QuotesRoomDatabase::class.java,"qq.db").createFromAsset("quotes.db").build()
                INSTANCE=instance
                instance
            }
        }
    }
}