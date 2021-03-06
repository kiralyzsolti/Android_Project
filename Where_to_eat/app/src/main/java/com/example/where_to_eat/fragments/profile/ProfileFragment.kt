package com.example.where_to_eat.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.where_to_eat.R
import com.example.where_to_eat.data.user.UserViewModel
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.adapter.MyAdapter
import com.example.where_to_eat.data.restaurant.RestaurantViewModel
import com.example.where_to_eat.retrofit.DataModel
import com.example.where_to_eat.retrofit.MainViewModel

class ProfileFragment : Fragment(), MyAdapter.OnItemClickListener {

    private val myAdapter by lazy { MyAdapter() }
    private lateinit var recyclerView: RecyclerView
    private lateinit var mRestaurantViewModel: RestaurantViewModel

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        name = view.findViewById(R.id.p_name)
        address = view.findViewById(R.id.p_address)
        email = view.findViewById(R.id.p_email)
        phone = view.findViewById(R.id.p_phone)
        image = view.findViewById(R.id.p_image)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readData.observe(viewLifecycleOwner, Observer { user ->
            if(user == null){
                findNavController().navigate(R.id.action_profileFragment_to_registrationFragment)
            }
            else{
                name.text = user.name
                address.text = user.address
                email.text = user.email
                phone.text = user.phone_num
                if(user.image?.size != null)
                    image.setImageBitmap(BitmapFactory.decodeByteArray(user.image, 0, user.image?.size!!))
            }
        })


        view.findViewById<Button>(R.id.p_update).setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_updateFragment)
        }

        return view;
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        mRestaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        mRestaurantViewModel.readAllFavouriteData.observe(viewLifecycleOwner, Observer{
            if(it != null){
                val myList = emptyList<DataModel>().toMutableList()
                for(item in it){
                    myList.add(DataModel(item.id,item.address,item.area,item.city,item.country,item.image_url,
                                                         item.lat, item.lng,item.mobile_reserve_url,item.name,item.phone,
                                                         item.postal_code,item.price,item.reserve_url,item.state))
                }
                myAdapter.setData(myList,this)
            }
        })

    }

    private fun setupRecyclerview(){
        recyclerView = view?.findViewById(R.id.p_recyclerView)!!
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }
    override fun onItemClick(position: Int) {
        findNavController().navigate(R.id.action_profileFragment_to_detailFragment)
    }
}