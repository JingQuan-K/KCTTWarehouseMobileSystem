package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import kotlinx.android.synthetic.main.material_type_view.view.*

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

    fun setData(newList: List<Material>){
        this.stockDetail = newList
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){}
}