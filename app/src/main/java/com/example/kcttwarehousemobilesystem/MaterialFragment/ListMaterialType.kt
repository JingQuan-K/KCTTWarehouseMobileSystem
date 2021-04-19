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

class ListMaterialType: RecyclerView.Adapter<ListMaterialType.MyViewHolder>() {

    private var materialTypeList = emptyList<MaterialType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.material_type_view, parent, false))
    }

    override fun getItemCount(): Int {
        return materialTypeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = materialTypeList[position]
        holder.itemView.button_materialType.text = currentItem.MaterialTypeName.toString()

        holder.itemView.button_materialType.setOnClickListener {view->
            val bundle = Bundle()
            bundle.putString("MaterialTypeName", materialTypeList[position].MaterialTypeName)
            bundle.putInt("id", materialTypeList[position].MaterialTypeId)
            view.findNavController().navigate(R.id.action_register_material_to_add_new_product, bundle)
        }
    }

    fun setData(newList: List<MaterialType>){
        this.materialTypeList = newList
        notifyDataSetChanged()
    }

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){}
}
