package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.ViewModel.MaterialVM
import kotlinx.android.synthetic.main.stock_details_normal.view.*
import kotlinx.coroutines.launch

class StockDetailNormal(val bundle: Bundle):Fragment() {

    private lateinit var stkViewModel: MaterialVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.stock_details_normal, container, false)

        val id:Int = bundle.getInt("id")
        val adapter = StockAdapter()
        val recyclerView = root.rvNormal
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dao = KCTTDatabase.getDatabase(requireContext()).userDao()
        stkViewModel = ViewModelProvider(this).get(MaterialVM::class.java)
        lifecycleScope.launch {
            stkViewModel.materialVal.value = dao.getMTIDMaterial(id)
        }
        stkViewModel.materialVal.observe(viewLifecycleOwner, Observer { material ->
            adapter.setData(material)
        })

        return root
    }
}