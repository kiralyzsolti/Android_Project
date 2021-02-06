package com.example.androidproject.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.androidproject.adapter.RestaurantAdapter
import com.example.androidproject.data.restaurant.Restaurant
import com.example.androidproject.data.retrofit.AllRestaurantsResponseData
import com.example.androidproject.repository.Repository
import com.example.androidproject.viewmodel.MainViewModel
import com.example.androidproject.viewmodel.MainViewModelFactory


class ListFragment : Fragment() {

   private lateinit var viewModel: MainViewModel
   private lateinit var list: MutableList<Restaurant>
   //private val restaurantAdapter by lazy{RestaurantAdapter()}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Set up Recyclerview
        val recyclerView = view.findViewById<RecyclerView>(R.id.restaurant_recyclerview)
        //recyclerView.adapter = restaurantAdapter
        //recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllRestaurants()

        viewModel.myResponseRestaurant.observe(viewLifecycleOwner, Observer {
            list = it
        })
        recyclerView.adapter = RestaurantAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

            //Log.d("Response", it[0].toString())

        return view
    }


}