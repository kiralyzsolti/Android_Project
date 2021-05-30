package com.example.where_to_eat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.where_to_eat.R
import com.example.where_to_eat.fragments.MainFragment
import com.example.where_to_eat.retrofit.MainViewModel
import com.example.where_to_eat.retrofit.MainViewModelFactory
import com.example.where_to_eat.retrofit.Repository

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
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
        MainFragment.viewModel = viewModel

        Handler().postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            },SPLASH_TIME_OUT.toLong())
    }
}