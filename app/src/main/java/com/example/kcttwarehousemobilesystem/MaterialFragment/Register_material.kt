package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.entity.UserDatabase
import com.example.kcttwarehousemobilesystem.entity.UserRepository
import com.example.kcttwarehousemobilesystem.entity.UserViewModel
import com.example.kcttwarehousemobilesystem.materialType.MaterialTypeVM
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_register_material.*
import kotlinx.android.synthetic.main.fragment_register_material.view.*
import kotlinx.android.synthetic.main.layout_add_material_type.*
import kotlinx.android.synthetic.main.layout_add_material_type.view.*

class Register_material : Fragment() {
    //For insert material type(alert dialog)
    private lateinit var mtViewModel: MaterialTypeVM

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_material, container, false)

        val adapter = ListMaterialType()
        val recyclerView = view.recyclerViewMaterialType
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //view model
        mtViewModel = ViewModelProvider(this).get(MaterialTypeVM::class.java)
        mtViewModel.getAllMaterialType.observe(viewLifecycleOwner, Observer { materialType ->
            adapter.setData(materialType)
        })

        view.registerMT.setOnClickListener{
            val addMTView = LayoutInflater.from(context).inflate(R.layout.layout_add_material_type, null)
            val addMTViewBuilder = AlertDialog.Builder(context, R.style.PopUpWindow).setView(addMTView).setTitle("Material Type Name")
            //show dialog
            val displayDialog = addMTViewBuilder.show()

            //Cancel
            addMTView.cancel_add_materialType.setOnClickListener{
                displayDialog.dismiss()
                Toast.makeText(requireContext(),"Cancelled", Toast.LENGTH_LONG).show()
            }
            //Add button clicked update database
            addMTView.add_materialType.setOnClickListener{
                val mt = addMTView.txt_materialType_pop.text.toString()

                if(!(TextUtils.isEmpty(mt))){
                    val mtToDatabase = MaterialType(0,mt)
                    mtViewModel.addMaterialType(mtToDatabase)
                    Toast.makeText(requireContext(),"Successfully added", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(),"Please fill in a new material type", Toast.LENGTH_LONG).show()
                }
                displayDialog.dismiss()
            }
        }

        /*toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()*/



        return view
    }
}