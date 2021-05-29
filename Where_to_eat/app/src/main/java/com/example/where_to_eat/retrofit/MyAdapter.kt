package com.example.where_to_eat.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.where_to_eat.R
import com.squareup.picasso.Picasso

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<DataModel>()

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
    }

    fun setData(newList: List<DataModel>){
        myList = newList
        notifyDataSetChanged()
    }
}