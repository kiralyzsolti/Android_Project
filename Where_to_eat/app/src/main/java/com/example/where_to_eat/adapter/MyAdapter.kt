package com.example.where_to_eat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.R
import com.example.where_to_eat.data.restaurant.RestaurantViewModel
import com.example.where_to_eat.fragments.restaurant.DetailFragment
import com.example.where_to_eat.retrofit.DataModel
import com.squareup.picasso.Picasso

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    companion object {
        lateinit var mRestaurantViewModel: RestaurantViewModel
    }

    private var myList = emptyList<DataModel>()
    private lateinit var listener: OnItemClickListener

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.restaurant_item, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Picasso.get().load(myList[position].image_url).into(holder.itemView.findViewById<ImageView>(R.id.i_restaurant))
        holder.itemView.findViewById<TextView>(R.id.i_title).text = myList[position].name
        holder.itemView.findViewById<TextView>(R.id.i_address).text = myList[position].address
        holder.itemView.findViewById<TextView>(R.id.i_price).text = myList[position].price.toString()

        holder.itemView.findViewById<ImageButton>(R.id.i_favouriteButton).setOnClickListener {
            mRestaurantViewModel.setRestAsFavourite(myList[position].id)
        }

        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                DetailFragment.rest = myList[position]
                listener.onItemClick(position)
            }
        })
    }

    fun setData(newList: List<DataModel>, newListener: OnItemClickListener){
        myList = newList
        listener = newListener
        notifyDataSetChanged()
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
}