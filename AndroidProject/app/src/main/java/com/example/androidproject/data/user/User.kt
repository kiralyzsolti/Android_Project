package com.example.androidproject.data.user

import androidx.room.*

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
        val id: Int,
    var name: String = "",
    var address: String = "",
    var phone: String = "",
    var email: String = "",
    var image: String = ""
)
