package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class StockDetailPagerAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int, val b: Bundle): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when(position){
            0 -> {
                val bundle = Bundle()
                var id: Int?=null
                id = b.getInt("id")
                bundle.putInt("id", id)
                StockDetailNormal(bundle)
            }
            1 -> {
                val bundle = Bundle()
                var id: Int?=null
                id = b.getInt("id")
                bundle.putInt("id", id)
                StockDetailReorder(bundle)
            }
            else -> getItem(position)
        }
    }

    /*override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }*/

    override fun getCount(): Int {
        // Show 2 total pages.
        return totalTabs
    }
}