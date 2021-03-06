package com.example.where_to_eat.data.restaurant

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantViewModel(application: Application): AndroidViewModel(application) {

    val readAllFavouriteData: LiveData<List<Restaurant>>
    private val repository: RestaurantRepository

    init{
        val restaurantDao = RestaurantDatabase.getDatabase(application).restaurantDao()
        repository = RestaurantRepository(restaurantDao)
        readAllFavouriteData = repository.readAllFavouriteData
    }

    fun addRestaurant(restaurant: Restaurant){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addRestaurant(restaurant)
        }
    }

    fun checkRestId(id: Int): LiveData<Int>{
        return repository.checkRestId(id)
    }

    fun selectRest(id: Int): LiveData<Restaurant>{
        return repository.selectRest(id)
    }

    fun setRestAsFavourite(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            repository.setRestAsFavourite(id)
        }
    }
}
