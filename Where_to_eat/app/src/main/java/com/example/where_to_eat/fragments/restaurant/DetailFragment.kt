package com.example.where_to_eat.fragments.restaurant

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.where_to_eat.R
import com.example.where_to_eat.data.restaurant.Restaurant
import com.example.where_to_eat.retrofit.DataModel
import com.example.where_to_eat.retrofit.MainViewModel
import com.example.where_to_eat.retrofit.MainViewModelFactory
import com.example.where_to_eat.retrofit.Repository
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    companion object{
        lateinit var rest: DataModel
    }

    private lateinit var name: TextView
    private lateinit var area: TextView
    private lateinit var city: TextView
    private lateinit var address: TextView
    private lateinit var country: TextView
    private lateinit var phone: TextView
    private lateinit var postal_code: TextView
    private lateinit var state: TextView
    private lateinit var price: TextView
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        name = view.findViewById(R.id.d_name)
        address = view.findViewById(R.id.d_address)
        area = view.findViewById(R.id.d_area)
        city = view.findViewById(R.id.d_city)
        country = view.findViewById(R.id.d_country)
        phone = view.findViewById(R.id.d_phone)
        postal_code = view.findViewById(R.id.d_postal_code)
        state = view.findViewById(R.id.d_state)
        price = view.findViewById(R.id.d_price)
        image = view.findViewById(R.id.d_image)

        name.text = rest.name
        address.text = rest.address
        area.text = rest.area
        city.text = rest.city
        country.text = rest.country
        phone.text = rest.phone
        postal_code.text = rest.postal_code
        state.text = rest.state
        price.text = rest.price.toString()
        Picasso.get().load(rest.image_url).into(image)

        return  view
    }

}