package com.example.androidproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.R
import com.example.androidproject.data.restaurant.Restaurant
import com.squareup.picasso.Picasso

class RestaurantAdapter(private val list: MutableList<Restaurant>): RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val pictureR: ImageView = itemView.findViewById(R.id.i_picture)
        val nameR: TextView = itemView.findViewById(R.id.i_restaurantName)
        val addressR: TextView = itemView.findViewById(R.id.i_restaurantAddress)
        val priceR: TextView = itemView.findViewById(R.id.i_restaurantPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant,parent,false)
        return RestaurantViewHolder(itemView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currItem = list[position]
       // Picasso.get().load(currItem.image_url).into(holder.pictureR)
        holder.nameR.text = currItem.name
        holder.addressR.text = currItem.address
        holder.priceR.text = currItem.price.toString()
    }

}