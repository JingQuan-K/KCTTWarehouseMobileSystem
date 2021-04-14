package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import kotlinx.android.synthetic.main.material_type_view.view.*

class ListMaterialType: RecyclerView.Adapter<ListMaterialType.MyViewHolder>() {

    private var materialTypeList = emptyList<MaterialType>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.material_type_view, parent, false))
    }

    override fun getItemCount(): Int {
        return materialTypeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //val currentItem = materialTypeList[position]
        holder.itemView.button_materialType.text = materialTypeList[position].MaterialTypeName.toString()
    }

    fun setData(newList: List<MaterialType>){
        materialTypeList = newList
        notifyDataSetChanged()
    }
}
