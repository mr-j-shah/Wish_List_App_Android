package com.crestinfosystems_jinay.wishlistapp.DataModel

import kotlinx.coroutines.flow.Flow

class WishRepo(private val wishDao: WishDao) {

    suspend fun addWish(wish: Wish){
        wishDao.addWish(wish)
    }

    fun getAllWishes() : Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWish(id:Long):Flow<Wish>{
        return  wishDao.getAWishById(id)
    }

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateAwish(wish)
    }
}