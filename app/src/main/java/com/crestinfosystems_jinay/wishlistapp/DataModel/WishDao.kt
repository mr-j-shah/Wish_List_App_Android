package com.crestinfosystems_jinay.wishlistapp.DataModel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addWish(wishEntity:Wish)

    // Loads all wishes from Table
    @Query("Select * FROM `Wish-List`")
    abstract fun getAllWishes() : Flow<List<Wish>>

    @Delete()
    abstract suspend fun deleteWish(wishEntity: Wish)

    @Update
    abstract suspend fun updateAwish(wishEntity: Wish)

    @Query("Select * FROM `Wish-List` where id=:id")
    abstract fun getAWishById(id:Long):Flow<Wish>
}