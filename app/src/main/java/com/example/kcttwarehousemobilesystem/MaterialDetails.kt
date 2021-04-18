package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_material_details.*

class MaterialDetails : AppCompatActivity() {

    companion object {
        const val MATERIAL_ID = "material_id"
        const val MATERIAL_NAME = "material_name"
        const val DEFAULT_QUANTITY = "1"
        const val QUANTITY = "quantity"
    }

    private var materialId = 0
    private var materialName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_details)

        //Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Material Details"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //get material id from intent
        if(intent?.extras?.getInt(MATERIAL_ID) != null){
            materialId = intent?.extras?.getInt(MATERIAL_ID).toString().toInt()
        }


        materialName = intent?.extras?.getString(MATERIAL_NAME).toString()

        //set text
        material_id.text = materialId.toString()
        material_name.text = materialName

        //set default quantity
        quantity_textField.text = Editable.Factory.getInstance().newEditable(DEFAULT_QUANTITY)

        //Decrease quantity
        minus_quantity.setOnClickListener{

            if(quantity_textField.text.toString().isNotEmpty()) {
                var quantity = quantity_textField.text.toString().toInt()
                if(quantity > 1){
                    quantity--
                    quantity_textField.text = Editable.Factory.getInstance().newEditable(quantity.toString())
                }
                else{
                    minus_quantity.isEnabled = false
                    //set colour
                    //...
                }
            }else{
                quantity_textField.text = Editable.Factory.getInstance().newEditable("1")
            }
        }

        //Increase quantity
        add_quantity.setOnClickListener{

            if(quantity_textField.text.toString().isNotEmpty()){
                var quantity = quantity_textField.text.toString().toInt()
                quantity++
                quantity_textField.text = Editable.Factory.getInstance().newEditable(quantity.toString())
                minus_quantity.isEnabled = true
                //set colour
                //...
            }else{
                quantity_textField.text = Editable.Factory.getInstance().newEditable("1")
            }

        }

        quantity_textField.addTextChangedListener {
            if(quantity_textField.text.toString().isNotEmpty()){
                if(quantity_textField.text.toString().toInt() > 0)
                    minus_quantity.isEnabled = true
            }
        }

        //check if quantity is valid
        next_activity_btn.setOnClickListener {

            if(quantity_textField.text.toString().isNotEmpty()){
                if(quantity_textField.text.toString().toInt() > 0 ){
                    val intent = Intent(this, PlaceToRackScanner::class.java)
                    var quantity = quantity_textField.text.toString().toInt()
                    intent.putExtra(MATERIAL_ID, materialId)
                    intent.putExtra(QUANTITY, quantity)
                    startActivity(intent)
                }else{
                    quantity_textField.error = "Quantity cannot be empty"
                    return@setOnClickListener
                }
            }else{
                quantity_textField.error = "Quantity cannot be empty"
                return@setOnClickListener
            }
        }

    }



}