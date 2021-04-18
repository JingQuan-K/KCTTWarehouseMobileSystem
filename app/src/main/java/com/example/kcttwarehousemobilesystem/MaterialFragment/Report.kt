package com.example.kcttwarehousemobilesystem.MaterialFragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.entity.Transactions
import kotlinx.android.synthetic.main.report.view.*
import kotlinx.android.synthetic.main.report2.view.*


class Report() : RecyclerView.Adapter<Report.MyViewHolder>() {

    private var materialList = emptyList<Material>()
    private var transactionsList = emptyList<Transactions>()
    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Report.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.report2, parent, false))
    }

    override fun onBindViewHolder(holder: Report.MyViewHolder, position: Int) {
        val materialDetail = materialList[position]
        val transactionDetail = transactionsList[position]
        holder.itemView.productId.text = materialDetail.MaterialId.toString()
        holder.itemView.stockIn.text = transactionDetail.StockIn.toString()
        holder.itemView.stockOut.text = transactionDetail.StockOut.toString()
        holder.itemView.totalQuantity.text = materialDetail.Quantity.toString()

    }

    fun setDataM(material: List<Material>){
        this.materialList = material
        notifyDataSetChanged()
    }

    fun setDataT(transactions: List<Transactions>){
        this.transactionsList = transactions
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return materialList.size
    }
}