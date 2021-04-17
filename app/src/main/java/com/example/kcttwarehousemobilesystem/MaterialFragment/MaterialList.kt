package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.materialType.MaterialTypeVM
import kotlinx.android.synthetic.main.fragment_material_list.view.*

class MaterialList : Fragment() {

    private lateinit var mtViewModel: MaterialTypeVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_material_list, container, false)

        val adapter = ListMaterialType2()
        val recyclerView = view.recyclerViewMaterialType2
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //view model
        mtViewModel = ViewModelProvider(this).get(MaterialTypeVM::class.java)
        mtViewModel.getAllMaterialType.observe(viewLifecycleOwner, Observer { materialType ->
            adapter.setData(materialType)
        })

        return view
    }
}