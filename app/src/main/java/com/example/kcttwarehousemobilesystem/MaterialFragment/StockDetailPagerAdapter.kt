package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
internal class StockDetailPagerAdapter(var context: Context, fm: FragmentManager, var totalTabs: Int): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return when(position){
            0 -> StockDetailNormal()
            1 -> StockDetailReorder()
            else -> StockDetailNormal()
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