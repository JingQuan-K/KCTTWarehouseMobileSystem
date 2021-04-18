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
import kotlinx.android.synthetic.main.fragment_stock_detail.view.*
import kotlinx.coroutines.launch

class StockDetail : Fragment() {

    private lateinit var stkViewModel: MaterialVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stock_detail, container, false)

        var id: Int?=null
        id = arguments?.getInt("id")
        if(id != null){

            val adapter = StockAdapter()
            val recyclerView = view.recyclerView
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val dao = UserDatabase.getDatabase(requireContext()).userDao()
            stkViewModel = ViewModelProvider(this).get(MaterialVM::class.java)
            lifecycleScope.launch {
                stkViewModel.materialVal.value = dao.getMTIDMaterial(id)
            }
            stkViewModel.materialVal.observe(viewLifecycleOwner, Observer { material ->
                adapter.setData(material)
            })
        }
        // Inflate the layout for this fragment
        return view
    }

}
