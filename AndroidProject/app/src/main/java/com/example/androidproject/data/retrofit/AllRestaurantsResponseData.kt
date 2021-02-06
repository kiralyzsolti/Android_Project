package com.example.androidproject.data.retrofit

import com.example.androidproject.data.restaurant.Restaurant

data class AllRestaurantsResponseData (
        val total_entries:Int,
        val page:Int,
        val per_page:Int,
        val restaurants:MutableList<Restaurant>
        )