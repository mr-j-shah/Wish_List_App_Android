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
)

object DummyData {
    val wishList = listOf<Wish>(
        Wish(
            title = "Buy a Apple Watch",
            desc = "You need to archive this Target by end of Year 2024",
            id = 0
        ),
        Wish(
            title = "Car",
            desc = "You need to archive this Target by end of Year 2024",
            id = 1
        )
    )
}
