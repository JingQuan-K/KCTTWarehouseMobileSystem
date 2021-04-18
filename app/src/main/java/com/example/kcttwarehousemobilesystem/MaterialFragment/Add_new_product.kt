package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.database.UserDao
import com.example.kcttwarehousemobilesystem.database.UserDatabase
import com.example.kcttwarehousemobilesystem.entity.Material
import com.example.kcttwarehousemobilesystem.materialType.MaterialTypeVM
import com.example.kcttwarehousemobilesystem.materialType.MaterialVM
import kotlinx.android.synthetic.main.fragment_add_new_product.*
import kotlinx.android.synthetic.main.fragment_add_new_product.view.*
import kotlinx.coroutines.launch

class Add_new_product : Fragment() {

    //private lateinit var mUserViewModel: UserViewModel
    private var selectedImage: Uri? = null
    //get material id to generate new id
    //private lateinit var mViewModel: MaterialVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new_product, container, false)

        //bundle argument from recycler view
        var materialTypeName: String? = null
        var id: Int?=null
        materialTypeName = arguments?.getString("MaterialTypeName")
        id = arguments?.getInt("id")
        view.txt_materialType.setText(materialTypeName)

        //generate new material id
        //mViewModel = ViewModelProvider(this).get(MaterialVM::class.java)
        //val allMaterial: LiveData<List<Material>>? = mViewModel.getAllMaterial
        val dao: UserDao = UserDatabase.getDatabase(requireContext()).userDao()


        lifecycleScope.launch {
            val materialList: List<Material> = dao.getListMaterial()
            if (materialList.isEmpty()) {
                view.txt_materialID.setText("1")
            }else{

                    var materialId:Int = dao.getLastMId() + 1
                    view.txt_materialID.setText(materialId.toString())
            }
        }


        view.btn_uploadPhoto.setOnClickListener{
           // uploadImage()
            Toast.makeText(activity,"testing 123123123", Toast.LENGTH_LONG).show()
        }
        return view
    }

    /*private fun uploadImage(){
        if(selectedImage == null){
            layout_registerP.snackbar("Select an image first")
            return
        }

        val parcelFileDescriptor =
            contentResolver.openFileDescriptor(selectedImage!!, "r", null)?: return
        val file = File(cacheDier)
    }*/

    private fun insertNewProductToDatabase() {
        val materialName = txt_materialName.text.toString()
        val costPI = txt_costPI.text

        if(inputCheck(materialName, costPI)){
            Toast.makeText(requireContext(),"testing123",Toast.LENGTH_LONG).show()
            //findNavController().navigate(R.id.register_material)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(materialName: String, costPI: Editable?): Boolean{
        return !(TextUtils.isEmpty(materialName) && (costPI==null))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.register_product_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview = item.itemId
        when(itemview){
            R.id.register_productBtn -> Toast.makeText(requireContext(), "Add Product", Toast.LENGTH_SHORT).show()
        }
        //return false
        return super.onOptionsItemSelected(item)
    }
}