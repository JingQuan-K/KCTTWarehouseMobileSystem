package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kcttwarehousemobilesystem.MaterialFragment.ReportAdapter
import com.example.kcttwarehousemobilesystem.materialType.MaterialVM

class Report : AppCompatActivity() {

    private lateinit var mMaterialVM: MaterialVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.report)

        val adapter = ReportAdapter()

    }
}