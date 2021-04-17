package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.*
import com.example.kcttwarehousemobilesystem.entity.Transactions
import com.example.kcttwarehousemobilesystem.entity.UserDatabase
import kotlinx.android.synthetic.main.activity_material_details.*
import kotlinx.coroutines.launch

class MaterialDetailsRetrieve : AppCompatActivity() {

    companion object {
        const val RACK_ID = "rack_id"
        const val MATERIAL_ID = "material_id"
        const val MATERIAL_NAME = "material_name"
        const val DEFAULT_QUANTITY = "1"
        const val QUANTITY = "quantity"
    }

    private var rackId = ""
    private var rackQuantity = 0
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
        rackId = intent?.extras?.getString(RACK_ID).toString()
        rackQuantity = intent?.extras?.getInt(QUANTITY).toString().toInt()
        materialId = intent?.extras?.getInt(MATERIAL_ID).toString().toInt()
        materialName = intent?.extras?.getString(MATERIAL_NAME).toString()

        //set text
        next_activity_btn.text = "Retrieve From Rack"
        material_id.text = materialId.toString()
        material_name.text = materialName

        //set default quantity
        quantity_textField.text = Editable.Factory.getInstance().newEditable(DEFAULT_QUANTITY)

        //Decrease quantity
        minus_quantity.setOnClickListener {
            if (quantity_textField.text.toString().isNotEmpty()) {
                var quantity = quantity_textField.text.toString().toInt()
                if (quantity > 1) {
                    quantity--
                    quantity_textField.text =
                        Editable.Factory.getInstance().newEditable(quantity.toString())
                } else {
                    minus_quantity.isEnabled = false
                    //set colour
                    //...
                }
            } else {
                quantity_textField.text = Editable.Factory.getInstance().newEditable("1")
            }

        }

        //Increase quantity
        add_quantity.setOnClickListener {

            if (quantity_textField.text.toString().isNotEmpty()) {
                var quantity = quantity_textField.text.toString().toInt()
                quantity++
                quantity_textField.text =
                    Editable.Factory.getInstance().newEditable(quantity.toString())
                minus_quantity.isEnabled = true
                //set colour
                //...
            } else {
                quantity_textField.text = Editable.Factory.getInstance().newEditable("1")
            }

        }

        quantity_textField.addTextChangedListener {
            if (quantity_textField.text.toString().isNotEmpty()) {
                if (quantity_textField.text.toString().toInt() > 0)
                    minus_quantity.isEnabled = true
            }

        }


        //check if quantity is valid
        next_activity_btn.setOnClickListener {

            if (quantity_textField.text.toString().isNotEmpty()) {
                if (quantity_textField.text.toString().toInt() > 0) {

                    var quantity = quantity_textField.text.toString().toInt()
                    if (quantity <= rackQuantity) {
                        updateDatabase(quantity)


                    } else {
                        quantity_textField.error = "Quantity exceeded!"
                    }

                } else {
                    quantity_textField.error = "Quantity cannot be empty"
                    return@setOnClickListener
                }
            } else {
                quantity_textField.error = "Quantity cannot be empty"
                return@setOnClickListener
            }
        }
    }

    private fun updateDatabase(quantity:Int){
        //Database
        val dao = UserDatabase.getDatabase(this@MaterialDetailsRetrieve).userDao()

        lifecycleScope.launch{
            //update material quantity
            var materialQuantity = dao.getMaterialQuantity(materialId)
            materialQuantity -= quantity
            var materialTotalValue = materialQuantity * dao.getMaterialCostPerItem(materialId)
            dao.setMaterialQuantity(materialQuantity, materialId)
            dao.setMaterialTotalValue(materialTotalValue, materialId)

            //update rack quantity
            var rQuantity = dao.getRackQuantity(rackId)
            rQuantity -= quantity
            dao.setRackQuantity(rQuantity, rackId)
            //if rack quantity id 0, it does not belong to a material id
            if(rQuantity == 0){
                dao.setRackMaterialId(0,rackId)
            }

            dao.addTransaction(Transactions(0,"Stock Out", quantity, materialId,21 ))
        }

        //intent
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MATERIAL_NAME, materialName)
        intent.putExtra(QUANTITY,quantity)
        startActivity(intent)
    }


}