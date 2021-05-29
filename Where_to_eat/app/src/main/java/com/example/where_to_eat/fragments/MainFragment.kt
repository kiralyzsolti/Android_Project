package com.example.where_to_eat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.R
import com.example.where_to_eat.retrofit.MainViewModel
import com.example.where_to_eat.retrofit.MainViewModelFactory
import com.example.where_to_eat.retrofit.MyAdapter
import com.example.where_to_eat.retrofit.Repository


class MainFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()

//        val repository = Repository()
//        val viewModelFactory = MainViewModelFactory(repository)
//        viewModel = activity?.let { ViewModelProvider(it, viewModelFactory).get(MainViewModel::class.java) }!!
//        viewModel.getAll()
        viewModel.myResponseA.observe(requireActivity(), Observer { response ->
            if(response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
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
}