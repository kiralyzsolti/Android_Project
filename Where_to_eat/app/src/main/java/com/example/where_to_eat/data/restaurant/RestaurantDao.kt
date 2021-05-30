package com.example.where_to_eat.data.restaurant

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RestaurantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM restaurants")
    fun readAllData(): LiveData<List<Restaurant>>

    @Query("SELECT COUNT(*) FROM restaurants WHERE id=:id")
    fun checkRestId(id: Int): LiveData<Int>
}
