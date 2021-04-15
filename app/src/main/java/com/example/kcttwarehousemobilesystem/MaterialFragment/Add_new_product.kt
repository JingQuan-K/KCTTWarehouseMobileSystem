package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import kotlinx.android.synthetic.main.fragment_add_new_product.*
import kotlinx.android.synthetic.main.fragment_add_new_product.view.*

class Add_new_product : Fragment() {

    //private lateinit var mUserViewModel: UserViewModel
    private var selectedImage: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new_product, container, false)

        view.testingAdd.setOnClickListener{
            insertNewProductToDatabase()
        }

        /*btn_uploadPhoto.setOnClickListener{
            uploadImage()
        }*/
        return view
    }

    /*private fun uploadImage(){
        if(selectedImage == null){
            layout_registerP.snackbar("Select an image first")
            return
        }

        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(selectedImage!!, "r", null)?: return
        val file = File(cacheDier)
    }*/

    private fun insertNewProductToDatabase() {
        val materialName = txt_materialName.text.toString()
        val costPI = txt_costPI.text

        if(inputCheck(materialName, costPI)){
            Toast.makeText(requireContext(),"testing123",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.register_material)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(materialName: String, costPI: Editable?): Boolean{
        return !(TextUtils.isEmpty(materialName) && (costPI==null))
    }
}