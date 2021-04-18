package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.text.Editable
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kcttwarehousemobilesystem.R
import kotlinx.android.synthetic.main.fragment_add_new_product.view.*
import kotlinx.android.synthetic.main.fragment_edit_product.*
import kotlinx.android.synthetic.main.fragment_edit_product.view.*

class EditProduct : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_product, container, false)

        //bundle argument from recycler view
        var materialName: String? = null
        var id: Int?=null
        var qty: Int? = null
        var ttlV: Double? = null
        var costPI: Double? = null
        var reorderLvl: Int? = null
        materialName = arguments?.getString("MaterialName")
        id = arguments?.getInt("id")
        qty = arguments?.getInt("ttlQ")
        costPI = arguments?.getDouble("costPI")
        reorderLvl = arguments?.getInt("reorderLvl")
        view.txt_materialIDE.setText(id.toString())
        view.txt_materialNameE.setText(materialName.toString())
        view.txt_quantity.setText(qty.toString())
        view.txt_costPIE.setText(costPI.toString())
        view.txt_reorderLvl.setText(reorderLvl.toString())


        return  view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_product_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview = item.itemId
        when(itemview){
            R.id.edit_product_btn -> {
                val totalValue = txt_costPIE.text.toString().toDouble() * txt_quantity.text.toString().toInt()
                Toast.makeText(context,"abc",Toast.LENGTH_LONG).show()
            }
        }
        //return false
        return super.onOptionsItemSelected(item)
    }
}