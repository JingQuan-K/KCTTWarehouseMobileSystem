package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
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
    //Tab things
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_stock_detail, container, false)


        tabLayout = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.view_pager)
        tabLayout.addTab(tabLayout.newTab().setText("Stock Details"))
        tabLayout.addTab(tabLayout.newTab().setText("Low Stock"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val activity = context as AppCompatActivity
        val adapter = StockDetailPagerAdapter(requireContext(), activity.supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })






        // Inflate the layout for this fragment
        return view
    }
}
