package com.crestinfosystems_jinay.wishlistapp

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.crestinfosystems_jinay.wishlistapp.DataModel.WishDataBase
import com.crestinfosystems_jinay.wishlistapp.DataModel.WishRepo

object Graph {
    lateinit var database: WishDataBase
    val wishRepository by lazy{
        WishRepo(wishDao = database.wishDao())
    }
    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishDataBase::class.java,"wishlist.db").addMigrations(migration_1_2).build()
    }
    val migration_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Add SQL commands for migrating from version 1 to version 2
            // For example, alter table, add new table, etc.
            database.execSQL("ALTER TABLE \"Wish-List\" ADD COLUMN isComplete INTEGER NOT NULL DEFAULT 0")
        }
    }
}