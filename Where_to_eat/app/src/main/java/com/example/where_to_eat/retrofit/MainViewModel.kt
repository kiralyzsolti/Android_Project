package com.example.where_to_eat.retrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<DataModel>> = MutableLiveData()
    val myResponseA: MutableLiveData<Response<List<DataModel>>> = MutableLiveData()

    fun getAll(){
        viewModelScope.launch {
            val response = repository.getAll()
            myResponseA.value = response
        }
    }

    //test
    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
}