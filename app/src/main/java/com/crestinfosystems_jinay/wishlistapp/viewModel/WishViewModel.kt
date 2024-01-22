package com.crestinfosystems_jinay.wishlistapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crestinfosystems_jinay.wishlistapp.DataModel.Wish
import com.crestinfosystems_jinay.wishlistapp.DataModel.WishRepo
import com.crestinfosystems_jinay.wishlistapp.Graph

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepo: WishRepo = Graph.wishRepository
) : ViewModel() {
    var wishTitle by mutableStateOf("")
    var wishDesc by mutableStateOf("")
    var wishDone by mutableStateOf(false)

    fun onWishTitleChange(newString :String){
        wishTitle = newString
    }
    fun onWishDescChange(newString:String){
        wishDesc=newString
    }

    lateinit var getAllWishes : Flow<List<Wish>>
    init {
        viewModelScope.launch {
            getAllWishes = wishRepo.getAllWishes()
        }
    }

    fun addWish(wish: Wish){
            viewModelScope.launch(Dispatchers.IO) {
                wishRepo.addWish(wish)
            }
    }

    fun getAwishById(id:Long):Flow<Wish>{
        return wishRepo.getWish(id)
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepo.updateWish(wish)
        }
    }
    fun doneWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepo.updateWish(wish.copy(isDone = true))
        }
    }
    fun unDoneWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepo.updateWish(wish.copy(isDone = false))
        }
    }
    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepo.deleteWish(wish)
        }
    }
}