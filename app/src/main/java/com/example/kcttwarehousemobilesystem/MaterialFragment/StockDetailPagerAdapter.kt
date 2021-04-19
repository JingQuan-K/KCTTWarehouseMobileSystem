package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/*private class TAB_TITLES{
    "Stock Details",
    "Low Stock"
}*/

@Suppress("DEPRECATION")
class StockDetailPagerAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int, private val b: Bundle): FragmentPagerAdapter(fm) {

    /*fun StockDetailPagerAdapter(fm: FragmentManager, numOfTabs: Int){
        super.fm
    }*/
    var bundle = Bundle()
    var id: Int?=null

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        id = b.getInt("id")
        bundle.putInt("id", id!!)
        return when(position){
            0 -> {
                StockDetailNormal(bundle)
            }
            1 -> {
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