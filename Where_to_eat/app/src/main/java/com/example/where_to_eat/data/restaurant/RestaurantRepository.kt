package com.example.where_to_eat.data.restaurant

import androidx.lifecycle.LiveData

class RestaurantRepository(private val restaurantDao: RestaurantDao) {
    val readAllFavouriteData: LiveData<List<Restaurant>> = restaurantDao.readAllFavouriteData()

    fun setRestAsFavourite(id: Int){
        restaurantDao.setRestAsFavourite(id)
    }

    fun selectRest(id: Int): LiveData<Restaurant>{
        return restaurantDao.selectRest(id)
    }
    fun checkRestId(id: Int): LiveData<Int>{
        return restaurantDao.checkRestId(id)
    }
    suspend fun addRestaurant(restaurant: Restaurant){
        restaurantDao.addRestaurant(restaurant)
    }
}
