package com.example.where_to_eat.fragments.profile

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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.where_to_eat.R
import com.example.where_to_eat.data.user.User
import com.example.where_to_eat.data.user.UserViewModel
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class RegistrationFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    companion object{
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val name = view.findViewById<EditText>(R.id.r_name)
        val address = view.findViewById<EditText>(R.id.r_address)
        val phone = view.findViewById<EditText>(R.id.r_phone)
        val email = view.findViewById<EditText>(R.id.r_email)

        view.findViewById<Button>(R.id.r_selectImage).setOnClickListener{
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                    when (PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.checkSelfPermission(this.requireContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                            // permission denied
                            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                            // show popup to request runtime permission
                            requestPermissions(permissions, PERMISSION_CODE)
                        }
                        else -> {
                            // permission already granted
                            pickImageFromGallery()
                        }
                    }
                }
                else -> {
                    // system OS is < Marshmallows
                    pickImageFromGallery()
                }
            }
        }

        view.findViewById<Button>(R.id.r_registration).setOnClickListener{
            if(name.length() == 0 || address.length() == 0 || phone.length() == 0 || email.length() == 0)
                Toast.makeText(this.requireContext(),"Please fill all fields!",Toast.LENGTH_SHORT).show()
            else
            {
                val image: ByteArray = imageViewToByte(view.findViewById(R.id.r_image))
                mUserViewModel.addUser(User(0,name.text.toString(),image,address.text.toString(),phone.text.toString(),email.text.toString()))
                Toast.makeText(this.requireContext(),"Successful registration!",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registrationFragment_to_profileFragment)
            }
        }
        return  view
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        this.startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun imageViewToByte(image: ImageView): ByteArray {
        val bitmap = (image.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)

        return stream.toByteArray()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission from popup granted
                    pickImageFromGallery()
                }
                else
                {
                    // permission from popup denied
                    Toast.makeText(this.requireContext(), "Permission denied!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val image = view?.findViewById<ImageView>(R.id.r_image)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image?.setImageURI(data?.data)
        }
    }

}