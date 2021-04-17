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
import com.example.kcttwarehousemobilesystem.materialType.MaterialVM
import kotlinx.android.synthetic.main.fragment_register_material.view.*

class StockDetail : Fragment() {

    private lateinit var stkViewModel: MaterialVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stock_detail, container, false)

        val adapter = StockAdapter()
        val recyclerView = view.recyclerViewMaterialType
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        stkViewModel = ViewModelProvider(this).get(MaterialVM::class.java)
        stkViewModel.getAllMaterial.observe(viewLifecycleOwner, Observer { material ->
            adapter.setData(material)
        })
        // Inflate the layout for this fragment
        return view
    }
}
