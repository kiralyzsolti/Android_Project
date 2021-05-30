package com.example.where_to_eat.data.restaurant

import androidx.lifecycle.LiveData

class RestaurantRepository(private val restaurantDao: RestaurantDao) {
    val readAllData: LiveData<List<Restaurant>> = restaurantDao.readAllData()

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
