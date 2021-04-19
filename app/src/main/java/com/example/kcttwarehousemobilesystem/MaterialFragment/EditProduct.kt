package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.database.UserDao
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import kotlinx.android.synthetic.main.fragment_add_new_product.view.*
import kotlinx.android.synthetic.main.fragment_edit_product.*
import kotlinx.android.synthetic.main.fragment_edit_product.view.*
import kotlinx.coroutines.launch

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


            }
        }
        //return false
        return super.onOptionsItemSelected(item)
    }

    private fun updateToDatabase(){
        val id = txt_materialIDE.text.toString().toInt()
        val name = txt_materialNameE.text.toString()
        val qty = txt_quantity.text.toString().toInt()
        val costPI = txt_costPIE.text.toString().toDouble()
        val reorderLvl = txt_reorderLvl.text.toString().toInt()
        if((id!= null) && !(TextUtils.isEmpty(name)) && (qty!=null) && (costPI!=null) && (reorderLvl!=null)) {
            val totalValue = txt_costPIE.text.toString().toDouble() * txt_quantity.text.toString().toInt()
            lifecycleScope.launch {
                val dao: UserDao = UserDatabase.getDatabase(requireContext()).userDao()
                dao.updateMaterial(name,qty,costPI,totalValue,reorderLvl,id)
            }
            Toast.makeText(context,"Successfully Updated",Toast.LENGTH_LONG).show()
            findNavController().navigateUp()
        }else{
            Toast.makeText(context,"Please fill out all fields",Toast.LENGTH_LONG).show()
        }
    }
}