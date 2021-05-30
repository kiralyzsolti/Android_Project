package com.example.where_to_eat.data.restaurant

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants")
    fun readAllData(): LiveData<List<Restaurant>>

    @Query("SELECT COUNT(*) FROM restaurants WHERE id=:id")
    fun checkRestId(id: Int): LiveData<Int>

    @Query("SELECT * FROM restaurants WHERE id=:id")
    fun selectRest(id: Int): LiveData<Restaurant>

    @Query("UPDATE restaurants SET favourite=1 WHERE id=:id")
    fun setRestAsFavourite(id: Int)
}
