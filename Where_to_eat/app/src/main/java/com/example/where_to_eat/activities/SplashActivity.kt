package com.example.where_to_eat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.where_to_eat.R
import com.example.where_to_eat.data.restaurant.Restaurant
import com.example.where_to_eat.data.restaurant.RestaurantViewModel
import com.example.where_to_eat.fragments.restaurant.MainFragment
import com.example.where_to_eat.retrofit.DataModel
import com.example.where_to_eat.retrofit.MainViewModel
import com.example.where_to_eat.retrofit.MainViewModelFactory
import com.example.where_to_eat.retrofit.Repository

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mRestaurantViewModel: RestaurantViewModel
    lateinit var tv: TextView
    lateinit var  iv: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tv = findViewById(R.id.tv)
        iv = findViewById(R.id.iv)

        supportActionBar?.hide()

        var myanim: Animation = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        tv.startAnimation(myanim)
        iv.startAnimation(myanim)

        val SPLASH_TIME_OUT = 5000
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAll()

        // Data add to MainFragment
        MainFragment.viewModel = viewModel

        // Data add to ROOM Database
        mRestaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        MainFragment.viewModel.myResponseA.observe(this, Observer { response ->
            if(response.isSuccessful)
                for(item:DataModel in response.body()!!)
                    // Check Rest Id
                    mRestaurantViewModel.checkRestId(item.id).observe(this, Observer {
                        if(it.equals(0)){
                            // Add to Database
                            mRestaurantViewModel.addRestaurant(Restaurant(item.id,
                                item.address,item.area,item.city,item.country,item.image_url,
                                item.lat,item.lng,item.mobile_reserve_url,item.name,item.phone,
                                item.postal_code,item.price,item.reserve_url,item.state,false))
                        }
                    })
        })


        Handler().postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            },SPLASH_TIME_OUT.toLong())
    }
}