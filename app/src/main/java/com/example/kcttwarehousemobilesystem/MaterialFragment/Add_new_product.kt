package com.example.kcttwarehousemobilesystem.MaterialFragment

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.Utils
import com.example.kcttwarehousemobilesystem.database.KCTTDao
import com.example.kcttwarehousemobilesystem.database.KCTTDatabase
import com.example.kcttwarehousemobilesystem.entity.Material
import kotlinx.android.synthetic.main.fragment_add_new_product.*
import kotlinx.android.synthetic.main.fragment_add_new_product.view.*
import kotlinx.coroutines.launch

class Add_new_product : Fragment() {

    //private lateinit var mUserViewModel: UserViewModel
    private var selectedImage: Uri? = null
    //get material id to generate new id
    //private lateinit var mViewModel: MaterialVM

    //photo
    val SELECT_PHOTO = 2222

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_new_product, container, false)

        //bundle argument from recycler view
        var materialTypeName: String? = null
        materialTypeName = arguments?.getString("MaterialTypeName")
        view.txt_materialType.setText(materialTypeName)

        //generate new material id
        //mViewModel = ViewModelProvider(this).get(MaterialVM::class.java)
        //val allMaterial: LiveData<List<Material>>? = mViewModel.getAllMaterial
        val dao: KCTTDao = KCTTDatabase.getDatabase(requireContext()).userDao()


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
            val  photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            startActivityForResult(photoPicker, SELECT_PHOTO)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == SELECT_PHOTO && resultCode == Activity.RESULT_OK && data != null){
            val pickedImage = data.data
            img_materialPhoto.setImageURI(pickedImage)
            //btn_save.isEnabled
        }
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
        val materialId = txt_materialID.text.toString().toInt()
        val materialName = txt_materialName.text.toString()
        val costPIText = txt_costPI.text.toString()
        var costPI: Double = 0.0
        var id: Int?=null
        id = arguments?.getInt("id")

        if(!TextUtils.isEmpty(costPIText)){
            costPI = costPIText.toDouble()
        }


        if(!(TextUtils.isEmpty(materialName)) && (costPI != 0.0) && img_materialPhoto.drawable != null){
            val bitmap = (img_materialPhoto.drawable as BitmapDrawable).bitmap
            //validate bitmap size
            val byteArray:ByteArray = Utils.getBytes(bitmap)
            if(byteArray.size < 1024*1024){
                val material = Material(materialId, materialName, byteArray, 0, costPI, 0.00, 10, id!!)
                lifecycleScope.launch {
                    val dao: KCTTDao = KCTTDatabase.getDatabase(requireContext()).userDao()
                    dao.addMaterial(material)
                }

                Toast.makeText(requireContext(), "New Material added successfully", Toast.LENGTH_SHORT).show()

                findNavController().navigateUp()
            }else{
                Toast.makeText(requireContext(), "The image size is too large", Toast.LENGTH_LONG).show()
            }


        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }

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
            R.id.register_productBtn -> {
                insertNewProductToDatabase()
            }
        }
        //return false
        return super.onOptionsItemSelected(item)
    }
}