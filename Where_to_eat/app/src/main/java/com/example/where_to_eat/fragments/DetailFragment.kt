package com.example.where_to_eat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.where_to_eat.R
import com.example.where_to_eat.retrofit.MainViewModel
import com.example.where_to_eat.retrofit.MainViewModelFactory
import com.example.where_to_eat.retrofit.Repository


class DetailFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)



        return  view
    }

}