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
import androidx.viewpager.widget.ViewPager
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.example.kcttwarehousemobilesystem.materialType.MaterialTypeVM
import com.example.kcttwarehousemobilesystem.materialType.MaterialVM
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_register_material.view.*
import kotlinx.android.synthetic.main.fragment_register_material.view.recyclerViewMaterialType
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

            /*val viewPager: ViewPager = findViewById(R.id.view_pager)
            val tabs: TabLayout = findViewById(R.id.tabs)
            tabs.setupWithViewPager(viewPager)*/
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



    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): StockDetail {
            return StockDetail().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}
