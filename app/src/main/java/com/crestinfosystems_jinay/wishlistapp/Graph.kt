package com.crestinfosystems_jinay.wishlistapp

import android.content.Context
import androidx.room.Room
import com.crestinfosystems_jinay.wishlistapp.DataModel.WishDataBase
import com.crestinfosystems_jinay.wishlistapp.DataModel.WishRepo

object Graph {
    lateinit var database: WishDataBase
    val wishRepository by lazy{
        WishRepo(wishDao = database.wishDao())
    }
    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDataBase::class.java,"wishlist.db").build()
    }
}