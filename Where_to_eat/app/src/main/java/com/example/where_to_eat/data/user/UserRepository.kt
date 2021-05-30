package com.example.where_to_eat.data.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readData: LiveData<User> = userDao.readData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    fun updateData(name: String, image:ByteArray, address: String, phone: String, email: String){
        userDao.updateData(name, image, address, phone, email)
    }
}
