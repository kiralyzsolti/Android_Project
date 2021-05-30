package com.example.where_to_eat.fragments.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.where_to_eat.R
import com.example.where_to_eat.data.user.UserViewModel
import java.io.ByteArrayOutputStream


@Suppress("DEPRECATION")
class UpdateFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var email: TextView
    private lateinit var phone: TextView
    private lateinit var image: ImageView

    companion object{
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        name = view.findViewById(R.id.u_name)
        address = view.findViewById(R.id.u_address)
        email = view.findViewById(R.id.u_email)
        phone = view.findViewById(R.id.u_phone)
        image = view.findViewById(R.id.u_image)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readData.observe(viewLifecycleOwner, Observer { user ->
            name.text = user.name
            address.text = user.address
            email.text = user.email
            phone.text = user.phone_num
            if(user.image?.size != null)
                image.setImageBitmap(BitmapFactory.decodeByteArray(user.image, 0, user.image?.size!!))
        })

        view.findViewById<Button>(R.id.u_selectImage).setOnClickListener{
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

        view.findViewById<Button>(R.id.u_update).setOnClickListener {
            if (name.length() == 0 || address.length() == 0 || phone.length() == 0 || email.length() == 0)
                Toast.makeText(this.requireContext(), "Please fill all fields!", Toast.LENGTH_SHORT)
                    .show()
            else {
                val imageU: ByteArray = imageViewToByte(image)
                mUserViewModel.updateData(name.text.toString(), imageU, address.text.toString(), phone.text.toString(), email.text.toString())
                Toast.makeText(this.requireContext(),"Successful update!",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateFragment_to_profileFragment)
            }
        }

        return view
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
        val image = view?.findViewById<ImageView>(R.id.u_image)
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image?.setImageURI(data?.data)
        }
    }

}