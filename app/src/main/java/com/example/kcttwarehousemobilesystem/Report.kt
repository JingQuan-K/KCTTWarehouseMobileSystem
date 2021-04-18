package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kcttwarehousemobilesystem.MaterialFragment.ListMaterialType2
import com.example.kcttwarehousemobilesystem.MaterialFragment.Report
import com.example.kcttwarehousemobilesystem.materialType.MaterialTypeVM
import com.example.kcttwarehousemobilesystem.materialType.MaterialVM
import com.example.kcttwarehousemobilesystem.materialType.TransactionsVM
import kotlinx.android.synthetic.main.fragment_material_list.view.*
import kotlinx.android.synthetic.main.report.*

class Report : AppCompatActivity() {

    private lateinit var mMaterialVM: MaterialVM
    private lateinit var rMaterialVM: TransactionsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)

        val adapter = Report()
        val recyclerView = this.recycleView_report
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //view model
        mMaterialVM = ViewModelProvider(this).get(MaterialVM::class.java)
        mMaterialVM.getAllMaterial.observe(this, Observer { material ->
            adapter.setDataM(material)
        })
        rMaterialVM = ViewModelProvider(this).get(TransactionsVM::class.java)
        rMaterialVM.getAllTransactions.observe(this, Observer { transactions ->
            adapter.setDataT(transactions)
        })

        setTitle("Report")


    }


}