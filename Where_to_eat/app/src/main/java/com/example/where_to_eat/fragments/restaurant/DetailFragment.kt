package com.example.where_to_eat.fragments.restaurant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.where_to_eat.R
import com.example.where_to_eat.data.restaurant.Restaurant
import com.example.where_to_eat.data.restaurant.RestaurantViewModel
import com.example.where_to_eat.fragments.profile.ProfileFragment
import com.example.where_to_eat.retrofit.DataModel
import com.example.where_to_eat.retrofit.MainViewModel
import com.example.where_to_eat.retrofit.MainViewModelFactory
import com.example.where_to_eat.retrofit.Repository
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private lateinit var mRestaurantViewModel: RestaurantViewModel

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

        view.findViewById<ImageButton>(R.id.d_locationButton).setOnClickListener{
            val gmmIntentUri = Uri.parse("google.streetview:cbll=" + rest.lat +","+rest.lng)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }

        view.findViewById<ImageButton>(R.id.d_phoneButton).setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + rest.phone)
            startActivity(dialIntent)
        }

        view.findViewById<ImageButton>(R.id.d_favouriteButton).setOnClickListener {
            mRestaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
            mRestaurantViewModel.setRestAsFavourite(rest.id)
            Toast.makeText(this.requireContext(),"Successful add to favourites!", Toast.LENGTH_SHORT).show()
        }

        return  view
    }

}