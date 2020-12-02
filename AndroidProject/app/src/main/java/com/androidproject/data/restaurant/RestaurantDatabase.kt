package com.androidproject.data.restaurant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidproject.data.user.User
import com.androidproject.data.user.UserDao

@Database(entities = [Restaurant::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase: RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object{
        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        fun getDatabase(context: Context): RestaurantDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        RestaurantDatabase::class.java,
                        "restaurants"
                ).build()
                INSTANCE = instance
                return  instance
            }
        }
    }

}