package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainMaterialList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_material_list)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment3)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}