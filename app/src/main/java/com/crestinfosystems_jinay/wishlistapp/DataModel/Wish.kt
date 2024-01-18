package com.crestinfosystems_jinay.wishlistapp.DataModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Wish-List")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wishDesc")
    val desc: String = "",
    @ColumnInfo(name = "wishTitle")
    val title: String = "",
    @ColumnInfo(name = "isComplete")
    val isDone :Boolean = false
)


