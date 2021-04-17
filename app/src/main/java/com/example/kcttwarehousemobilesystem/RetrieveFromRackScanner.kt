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
import com.example.kcttwarehousemobilesystem.entity.UserDatabase
import kotlinx.android.synthetic.main.scanner.*

private const val CAMERA_REQUEST_CODE = 101

class RetrieveFromRackScanner : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scanner)
        setupPermissions()
        codeScanner()

        //Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Retrieve from Rack"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //set scanner text
        scanner_text.text = "Scan Rack Barcode"

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

                //Get from Database
                val dao = UserDatabase.getDatabase(this@RetrieveFromRackScanner).userDao()

                val rackId = it.text
                //check if exist in database
                if(dao.rackExists(rackId)) {
                    val rack = dao.getRack(rackId)

                    //check if rack is empty
                    if(rack.Quantity > 0){
                        val material = dao.getMaterial(rack.MaterialId)
                        //intent
                        val intent = Intent(this@RetrieveFromRackScanner, MaterialDetailsRetrieve::class.java)
                        intent.putExtra(MaterialDetailsRetrieve.RACK_ID, rackId)
                        intent.putExtra(MaterialDetailsRetrieve.QUANTITY, rack.Quantity)
                        intent.putExtra(MaterialDetailsRetrieve.MATERIAL_ID, material.MaterialId)
                        intent.putExtra(MaterialDetailsRetrieve.MATERIAL_NAME, material.MaterialName)
                        startActivity(intent)

                    }else{
                        //Rack is empty
                        runOnUiThread {
                            scanner_message.text = "This Rack is empty"
                        }
                    }

                }else{
                    //Rack does not exist
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