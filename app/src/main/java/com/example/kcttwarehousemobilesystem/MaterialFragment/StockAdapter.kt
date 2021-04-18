package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.Material

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

class StockAdapter: RecyclerView.Adapter<StockAdapter.MyViewHolder>() {

    private var stockDetail = emptyList<Material>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stock_detail, parent, false))
    }

    override fun getItemCount(): Int {
        return stockDetail.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = stockDetail[position]
    }

/*    fun getPageTitle(position: Int): CharSequence? {
        //return context.resources.getString(TAB_TITLES[position])
    }*/

    fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
    fun setData(newList: List<Material>){
        this.stockDetail = newList
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return StockDetail.newInstance(position + 1)
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){}
}