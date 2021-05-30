package com.example.where_to_eat.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String ="",
    var image: ByteArray? = null,
    var address: String = "",
    var phone_num: String = "",
    var email: String = ""
)
