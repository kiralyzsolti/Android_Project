package com.example.androidproject.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.R
import com.example.androidproject.data.user.User
import com.example.androidproject.data.user.UserViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.util.regex.Pattern


class RefreshProfileFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var image: String

    companion object{
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
        private var prof = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_refreshprofile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = view.findViewById<EditText>(R.id.r_name)
        val address = view.findViewById<EditText>(R.id.r_address)
        val phone = view.findViewById<EditText>(R.id.r_phone)
        val email = view.findViewById<EditText>(R.id.r_email)
        val picture_button = view.findViewById<Button>(R.id.r_picture_button)
        val picture = view.findViewById<ImageView>(R.id.r_picture)

        picture_button.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(ActivityCompat.checkSelfPermission(this.requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                {
//                    permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else
                {
//                    permission already granted
                    pickImageFromGallery()
                }
            }
            else
            {
//                system OS is < Marshmallows
                pickImageFromGallery()
            }
        }


        view.findViewById<Button>(R.id.r_insert).setOnClickListener {
            if(inputCheck(name,email,phone,address)){
                mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                //Convert image to bytearray
                //val image: ByteArray = imageViewToByte(picture)
                //Create user
                val user = User(0, name.text.toString(), address.text.toString(), phone.text.toString(), email.text.toString(), image)
                //Add new User to database
                mUserViewModel.addUser(user)
                Snackbar.make(this.requireView(), "Data successfully added to database!", Snackbar.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_refreshFragment_to_profileFragment)
            }
            else{
                Snackbar.make(this.requireView(), "Invalid!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun imageViewToByte(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)

        return stream.toByteArray()
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else
                {
                    //permission from popup denied
                    Snackbar.make(this.requireView(), "Permission denied!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val imageView = view?.findViewById<ImageView>(R.id.r_picture)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            //image?.setImageURI(data?.data)
            imageView?.let {
                Glide.with(this)
                        .load(data?.data)
                        .override(50,50)
                        .into(it)
            }
            image = data?.data.toString()
        }
    }

    private fun inputCheck(name: EditText, email: EditText, phone: EditText, address: EditText): Boolean {
        return when {
            name.text.toString().isEmpty() -> {
                name.error = "Please fill it out!"
                false
            }
            address.text.toString().isEmpty() -> {
                address.error = "Please fill it out!"
                false
            }
            else -> (validateEmail(email) && validatePhone(phone))
        }
    }
    private fun validateEmail(email: EditText):Boolean {
        val emailT = email.text.toString()

        return if(emailT.isEmpty()) {
            email.error = "Please fill it out!"
            false
        }else if(!Pattern.compile("^[a-zA-Z0-9_.]+@[a-zA-Z0-9_.]+.[a-z]{2,}\$").matcher(emailT).matches()) {
            email.error = "Invalid email address!"
            false
        }else {
            true
        }
    }
    private fun validatePhone(phone: EditText):Boolean {
        val phoneT = phone.text.toString()

        return if(phoneT.isEmpty()){
            phone.error = "Please fill it out!"
            false
        }else if(!Pattern.compile("^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}\$").matcher(phoneT).matches()){
            phone.error = "Invalid phone number!"
            false
        }else{
            true
        }
    }
}