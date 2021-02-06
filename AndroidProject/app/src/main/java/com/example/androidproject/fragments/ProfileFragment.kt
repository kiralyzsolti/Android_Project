package com.example.androidproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.R
import com.example.androidproject.data.user.UserViewModel
import com.bumptech.glide.Glide


class ProfileFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val name = view.findViewById<TextView>(R.id.p_name)
        val address = view.findViewById<TextView>(R.id.p_address)
        val phone = view.findViewById<TextView>(R.id.p_phone)
        val email = view.findViewById<TextView>(R.id.p_email)
        val image = view.findViewById<ImageView>(R.id.p_imageView)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readUser().observe(requireActivity(), Observer{
            if(it != null){
                name.text = it.name
                address.text = it.address
                phone.text = it.phone
                email.text = it.email
               /* Glide.with(this)
                    .load(it.image)
                    .override(50,50)
                    .into(image)*/
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.p_editProfile).setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_refreshFragment)
        }
    }
}