package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.kcttwarehousemobilesystem.R
import com.google.android.material.tabs.TabLayout

class StockDetail : Fragment() {
    //Tab things
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    val bundle = Bundle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stock_detail, container, false)

        /*var id: Int? = arguments?.getInt("id")
        id?.let { bundle.putInt("id", it) }*/
        var id: Int? = arguments?.getInt("id")
        bundle.putInt("id", id!!)


        tabLayout = view.findViewById(R.id.tabs)
        viewPager = view.findViewById(R.id.view_pager)
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val activity = context as AppCompatActivity
        val adapter = StockDetailPagerAdapter(requireContext(), activity.supportFragmentManager, tabLayout.tabCount, bundle)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_stockdetails)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_warning_24)
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
