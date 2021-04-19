package com.example.kcttwarehousemobilesystem.MaterialFragment


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.Transactions
import kotlinx.android.synthetic.main.report2.view.*


class Report() : RecyclerView.Adapter<Report.MyViewHolder>() {

    private var transactionsList = emptyList<Transactions>()

    var material:Int = 0

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Report.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.report2, parent, false))
    }

    override fun onBindViewHolder(holder: Report.MyViewHolder, position: Int) {
        val transactionDetail = transactionsList[position]

        holder.itemView.quantity.text = transactionDetail.Quantity.toString()
        holder.itemView.transactionType.text = transactionDetail.TransactionType.toString()
        holder.itemView.productId.text = transactionDetail.MaterialId.toString()
        holder.itemView.transactionId.text = transactionDetail.TransactionId.toString()
    }

    fun setDataT(transactions: List<Transactions>){
        this.transactionsList = transactions
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }
}


