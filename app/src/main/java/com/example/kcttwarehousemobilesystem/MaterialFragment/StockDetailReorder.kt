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
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.example.kcttwarehousemobilesystem.materialType.MaterialVM
import kotlinx.android.synthetic.main.stock_details_normal.view.*
import kotlinx.android.synthetic.main.stock_details_normal.view.rvNormal
import kotlinx.android.synthetic.main.stock_details_reorder.view.*
import kotlinx.coroutines.launch

class StockDetailReorder(val bundle: Bundle):Fragment() {

    private lateinit var stkViewModel: MaterialVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.stock_details_reorder, container, false)

        val id:Int = bundle.getInt("id")
        val adapter = StockAdapter()
        val recyclerView = root.recyclerViewReorder
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dao = UserDatabase.getDatabase(requireContext()).userDao()
        stkViewModel = ViewModelProvider(this).get(MaterialVM::class.java)
        lifecycleScope.launch {
            stkViewModel.materialValLow.value = dao.getMTIDMaterialLow(id)
        }
        stkViewModel.materialValLow.observe(viewLifecycleOwner, Observer { material ->
            adapter.setData(material)
        })

        return root
    }
}