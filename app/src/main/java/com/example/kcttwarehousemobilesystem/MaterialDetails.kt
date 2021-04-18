package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import kotlinx.android.synthetic.main.activity_material_details.*
import kotlinx.coroutines.launch

class MaterialDetails : AppCompatActivity() {

    companion object {
        const val MATERIAL_ID = "material_id"
        const val DEFAULT_QUANTITY = "1"
        const val QUANTITY = "quantity"
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

        //SET IMAGE__________________________________________
        val dao = UserDatabase.getDatabase(this).userDao()
        lifecycleScope.launch {
            val image = dao.getImageOfMaterial(materialId.toInt())
            val bitmap:Bitmap = Utils.getImage(image)
            material_image.setImageBitmap(bitmap)
        }

        //set text
        material_id.text = materialId
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
        place_to_rack_btn.setOnClickListener {

            if(quantity_textField.text.toString().isNotEmpty()){
                if(quantity_textField.text.toString().toInt() > 0 ){
                    val intent = Intent(this, PlaceToRackScanner::class.java)
                    var quantity = quantity_textField.text.toString().toInt()
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