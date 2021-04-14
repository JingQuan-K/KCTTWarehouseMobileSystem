package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.entity.UserViewModel
import kotlinx.android.synthetic.main.fragment_register_material.view.*
import kotlinx.android.synthetic.main.layout_add_material_type.*
import kotlinx.android.synthetic.main.layout_add_material_type.view.*

class Register_material : Fragment() {

    private lateinit var mtViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_material, container, false)

        mtViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.registerMT.setOnClickListener{
            val addMTView = LayoutInflater.from(context).inflate(R.layout.layout_add_material_type, null)
            val addMTViewBuilder = AlertDialog.Builder(context, R.style.PopUpWindow).setView(addMTView).setTitle("Material Type Name")
            //show dialog
            val displayDialog = addMTViewBuilder.show()

            //Cancel
            addMTView.cancel_add_materialType.setOnClickListener{
                displayDialog.dismiss()
            }
            //Add button clicked update database
            addMTView.add_materialType.setOnClickListener{
                insertMaterialType()
            }
            //login button click of custom layout
            /*mDialogView.dialogLoginBtn.setOnClickListener {
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from EditTexts of custom layout
                val name = mDialogView.dialogNameEt.text.toString()
                val email = mDialogView.dialogEmailEt.text.toString()
                val password = mDialogView.dialogPasswEt.text.toString()
                //set the input text in TextView
                mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
            }*/

        }


        return view
    }

    private fun insertMaterialType() {
        val mt = txt_materialType_pop.text.toString()

        if(inputCheck(mt)){
            val mtToDatabase = MaterialType(0,mt)
            mtViewModel.addMaterialType(mtToDatabase)
            Toast.makeText(requireContext(),"Successfully added", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(),"Please fill in a new material type", Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(mt: String): Boolean{
        return !(TextUtils.isEmpty(mt))
    }
}