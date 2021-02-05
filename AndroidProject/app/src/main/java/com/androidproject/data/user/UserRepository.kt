package com.androidproject.data.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    fun readUser():LiveData<User>{
        return userDao.readUser()
    }
}