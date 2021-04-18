package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import kotlinx.android.synthetic.main.material_type_view.view.*
import kotlinx.android.synthetic.main.report2.view.*
import kotlinx.android.synthetic.main.stock_detail.view.*

class StockAdapter: RecyclerView.Adapter<StockAdapter.MyViewHolder>() {

    private var stockDetail = emptyList<Material>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.stock_detail, parent, false))
    }

    override fun getItemCount(): Int {
        return stockDetail.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val material = stockDetail[position]
        holder.itemView.material_id.text = material.MaterialId.toString()
        holder.itemView.material_name.text = material.MaterialName.toString()
        holder.itemView.total_quantity.text = material.Quantity.toString()
        holder.itemView.total_value.text = material.totalValue.toString()

        holder.itemView.edit_btn.setOnClickListener {view->
            val bundle = Bundle()
            bundle.putString("MaterialName", stockDetail[position].MaterialName)
            bundle.putInt("id", stockDetail[position].MaterialId)
            bundle.putInt("ttlQ", stockDetail[position].Quantity)
            bundle.putDouble("ttlV", stockDetail[position].totalValue)
            bundle.putDouble("costPI", stockDetail[position].CostPI)
            bundle.putInt("reorderLvl", stockDetail[position].reorderLvl)
            view.findNavController().navigate(R.id.action_stockDetail_to_editProduct, bundle)
        }
    }
    fun setData(newList: List<Material>){
        this.stockDetail = newList
        notifyDataSetChanged()
    }


    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){}
}