package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.budiyev.android.codescanner.CodeScanner
import com.example.kcttwarehousemobilesystem.entity.UserDatabase
import kotlinx.android.synthetic.main.scanner.*

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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

                    //Check if material belongs to rack
                    val rackList = dao.getRackOfSpecificMaterial(materialId.toInt())
                    var isBelongsTo = false
                    rackList.forEach { it ->
                        if (it == rackId)
                            isBelongsTo = true
                    }

                    if(isBelongsTo){
                        //update material quantity
                        var materialQuantity = dao.getMaterialQuantity(materialId.toInt())
                        materialQuantity += quantity.toInt()
                        dao.setMaterialQuantity(materialQuantity, materialId.toInt())

                        //update rack quantity
                        var rackQuantity = dao.getRackQuantity(rackId)
                        rackQuantity += quantity.toInt()
                        dao.setRackQuantity(rackQuantity, rackId)

                        //intent
                        val intent = Intent(this@PlaceToRackScanner, MainActivity::class.java)
                        intent.putExtra(RACK_ID, rackId)
                        startActivity(intent)
                    }
                    else{
                        //Material does not belong to this rack!
                        runOnUiThread {
                            scanner_message.text = "The Material does not belong on this Rack"
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