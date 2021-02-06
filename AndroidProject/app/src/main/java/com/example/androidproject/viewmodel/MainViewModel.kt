package com.example.androidproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.data.restaurant.Restaurant
import com.example.androidproject.data.retrofit.AllRestaurantsResponseData
import com.example.androidproject.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponseRestaurant:MutableLiveData<MutableList<Restaurant>> = MutableLiveData<MutableList<Restaurant>>()

    fun getAllRestaurants(){
        viewModelScope.launch {
            val response: Response<AllRestaurantsResponseData> = repository.getAllRestaurants()
            myResponseRestaurant.value = response.body()?.restaurants
        }
    }

}