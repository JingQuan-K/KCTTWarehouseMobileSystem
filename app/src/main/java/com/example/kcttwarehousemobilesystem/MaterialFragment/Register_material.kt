package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import com.example.kcttwarehousemobilesystem.materialType.MaterialTypeVM
import kotlinx.android.synthetic.main.fragment_register_material.view.*
import kotlinx.android.synthetic.main.layout_add_material_type.view.*

class Register_material : Fragment() {
    private lateinit var mtViewModel: MaterialTypeVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_material, container, false)

        //recycler view
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

        //Floating button to register material type
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
        return view
    }
}