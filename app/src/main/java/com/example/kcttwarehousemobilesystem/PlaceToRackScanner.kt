package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.budiyev.android.codescanner.*
import com.budiyev.android.codescanner.CodeScanner
import com.example.kcttwarehousemobilesystem.entity.Transactions
import com.example.kcttwarehousemobilesystem.entity.UserDao
import com.example.kcttwarehousemobilesystem.entity.UserDatabase
import kotlinx.android.synthetic.main.scanner.*
import kotlinx.coroutines.launch

private const val CAMERA_REQUEST_CODE = 101


class PlaceToRackScanner : AppCompatActivity() {

    companion object {
        const val RACK_ID = "rack_id"

    }

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scanner)
        setupPermissions()
        codeScanner()

        //Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Place to Rack"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //set scanner text
        scanner_text.text = "Scan Rack Barcode"



    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var data = Intent()
        setResult(RESULT_OK, data)
        finish()
        return true
    }



    private fun codeScanner(){
        codeScanner = CodeScanner(this, scanner_view)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {
                //get id & quantity
                val rackId: String = it.text
                val materialId = intent?.extras?.getInt(MaterialDetails.MATERIAL_ID).toString()
                val quantity = intent?.extras?.getInt(MaterialDetails.QUANTITY).toString()

                //Database
                val dao = UserDatabase.getDatabase(this@PlaceToRackScanner).userDao()
                //check if rack exists in database
                if(dao.rackExists(rackId)) {
                    val rackList = dao.getRackOfSpecificMaterial(materialId.toInt())
                    var isBelongsTo = false

                    //Check if material belongs to rack
                    rackList.forEach { rack ->
                        if (rack == rackId)
                            isBelongsTo = true
                    }

                    if(isBelongsTo){
                        lifecycleScope.launch {
                            //update material quantity & value
                            updateMaterialDB(dao, materialId, quantity)

                            //update rack quantity
                            var rackQuantity = dao.getRackQuantity(rackId)
                            rackQuantity += quantity.toInt()
                            dao.setRackQuantity(rackQuantity, rackId)

                            dao.addTransaction(Transactions(0,"Stock In", quantity.toInt(), materialId.toInt(),21 ))
                        }
                        intent
                        val intent = Intent(this@PlaceToRackScanner, MainActivity::class.java)
                        intent.putExtra(RACK_ID, rackId)
                        startActivity(intent)
                    }
                    else{
                        //check if rack is empty
                        if(dao.getRQty(rackId) > 0){
                            //This rack belongs to another Material
                            runOnUiThread {
                                scanner_message.text = "This Rack belongs to another Material"
                            }
                        }
                        else{
                            //Rack is Empty
                            lifecycleScope.launch{
                                //update material quantity & value
                                updateMaterialDB(dao, materialId, quantity)

                                //set rack material id & quantity
                                dao.setRackMaterialId(materialId.toInt(), rackId)
                                dao.setRackQuantity(quantity.toInt(), rackId)

                                dao.addTransaction(Transactions(0,"Stock In", quantity.toInt(), materialId.toInt(),21 ))
                            }

                            //intent
                            val intent = Intent(this@PlaceToRackScanner, MainActivity::class.java)
                            intent.putExtra(RACK_ID, rackId)
                            startActivity(intent)
                        }
                    }
                }else{
                    //This rack does not exist!
                    runOnUiThread {
                        scanner_message.text = "This Rack does not exist"
                    }
                }
            }

            errorCallback = ErrorCallback {
                runOnUiThread {
                    Log.e("Main", "Camera initialization error: ${it.message}")
                }
            }
        }

        scanner_view.setOnClickListener{
            codeScanner.startPreview()
        }
    }

    private fun updateMaterialDB(dao:UserDao, materialId:String, quantity:String){
        lifecycleScope.launch{
            var materialQuantity = dao.getMaterialQuantity(materialId.toInt())
            materialQuantity += quantity.toInt()
            var materialTotalValue = materialQuantity * dao.getMaterialCostPerItem(materialId.toInt())
            dao.setMaterialQuantity(materialQuantity, materialId.toInt())
            dao.setMaterialTotalValue(materialTotalValue, materialId.toInt())
        }
    }

    override fun onResume(){
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause(){
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions(){
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)

        if(permission != PackageManager.PERMISSION_GRANTED){
            makeRequest()
        }
    }

    private fun makeRequest(){
        ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                            this,
                            "You need the camera permission to be able to use this app!",
                            Toast.LENGTH_SHORT
                    ).show()
                } else {
                    //successful
                }
            }
        }
    }


}