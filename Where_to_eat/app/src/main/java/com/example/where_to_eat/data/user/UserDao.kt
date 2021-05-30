package com.example.where_to_eat.data.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users WHERE id=1")
    fun readData(): LiveData<User>

    @Query("UPDATE users SET name=:name, image=:image, address=:address, phone_num=:phone, email=:email WHERE id==1")
    fun updateData(name: String, image:ByteArray, address: String, phone: String, email: String)
}
