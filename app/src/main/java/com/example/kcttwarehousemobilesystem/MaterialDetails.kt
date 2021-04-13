package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material_details.*

class MaterialDetails : AppCompatActivity() {

    companion object {
        const val MATERIAL_ID = "material_id"
        const val DEFAULT_QUANTITY = "1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_details)

        //Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Material Details"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //get material id from intent
        val materialId = intent?.extras?.getString(MATERIAL_ID).toString()

        //set text
        material_id.text = materialId
        //set default quantity
        quantity_textField.text = Editable.Factory.getInstance().newEditable(DEFAULT_QUANTITY)

        //Decrease quantity
        minus_quantity.setOnClickListener{
            var quantity = quantity_textField.text.toString().toInt()
            if(quantity != 0){
                quantity--
                quantity_textField.text = Editable.Factory.getInstance().newEditable(quantity.toString())
            }
            else{
                minus_quantity.isEnabled = false
                //set colour
                //....
            }
        }

        //Increase quantity
        add_quantity.setOnClickListener{
            var quantity = quantity_textField.text.toString().toInt()
            quantity++
            quantity_textField.text = Editable.Factory.getInstance().newEditable(quantity.toString())
            minus_quantity.isEnabled = true
            //set colour
            //....
        }


    }
}