package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.MaterialType
import kotlinx.android.synthetic.main.material_type_view.view.*
import kotlinx.android.synthetic.main.material_type_view2.view.*

class ListMaterialType2: RecyclerView.Adapter<ListMaterialType2.MyViewHolder>() {
    private var materialTypeList = emptyList<MaterialType>()
    val bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.material_type_view2, parent, false))
    }

    override fun getItemCount(): Int {
        return materialTypeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = materialTypeList[position]
        holder.itemView.button_materialType2.text = currentItem.MaterialTypeName.toString()

        holder.itemView.button_materialType2.setOnClickListener {view->

            bundle.putInt("id", materialTypeList[position].MaterialTypeId)
            view.findNavController().navigate(R.id.action_materialList_to_stockDetail, bundle)
        }
    }

    fun setData(newList: List<MaterialType>){
        this.materialTypeList = newList
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){}
}