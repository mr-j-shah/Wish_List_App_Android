package com.crestinfosystems_jinay.wishlistapp.DataModel

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Wish::class],
    version = 2,
    exportSchema = false
)
abstract class WishDataBase :RoomDatabase() {
    abstract fun wishDao():WishDao
}