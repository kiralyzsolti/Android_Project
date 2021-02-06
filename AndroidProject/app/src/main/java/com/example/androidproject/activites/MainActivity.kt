package com.example.androidproject.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.R
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodel.MainViewModel
import com.example.androidproject.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  val repository = Repository()
      //  val viewModelFactory = MainViewModelFactory(repository)
      /* viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("Response",response.body()?.id.toString())
                Log.d("Response", response.body()?.name.toString())
                Log.d("Response",response.body()?.address!!)
                Log.d("Response",response.body()?.city!!)
                Log.d("Response",response.body()?.area!!)
                Log.d("Response",response.body()?.postal_code!!)
                Log.d("Response",response.body()?.country!!)
                Log.d("Response",response.body()?.phone!!)
                Log.d("Response",response.body()?.lat.toString())
                Log.d("Response",response.body()?.lng.toString())
                Log.d("Response",response.body()?.price.toString())
                Log.d("Response",response.body()?.reserve_url!!)
                Log.d("Response",response.body()?.mobile_reserve_url!!)
                Log.d("Response",response.body()?.image_url!!)
                Log.d("Response",response.body()?.state!!)
            } else{
              Log.d("Response", response.errorBody().toString())
            }


        })*/
    }
}