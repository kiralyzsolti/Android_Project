package com.example.where_to_eat.fragments.restaurant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.R
import com.example.where_to_eat.adapter.MyAdapter
import com.example.where_to_eat.data.restaurant.RestaurantViewModel
import com.example.where_to_eat.data.user.UserViewModel
import com.example.where_to_eat.retrofit.MainViewModel


class MainFragment : Fragment(), MyAdapter.OnItemClickListener {

    companion object {
        lateinit var viewModel: MainViewModel
    }
    private val myAdapter by lazy { MyAdapter() }
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()

        MyAdapter.mRestaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        viewModel.myResponseA.observe(requireActivity(), { response ->
            if(response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it,this) }

            }
            else{
                Toast.makeText(this.context,response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setupRecyclerview(){
        recyclerView = view?.findViewById(R.id.recycler_view)!!
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
    }
}