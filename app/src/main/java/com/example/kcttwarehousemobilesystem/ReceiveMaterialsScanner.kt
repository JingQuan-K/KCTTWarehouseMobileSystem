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
import kotlinx.android.synthetic.main.scanner.*

private const val CAMERA_REQUEST_CODE = 101

class ReceiveMaterialsScanner : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scanner)
        setupPermissions()
        codeScanner()

        //Action Bar
        val actionBar = supportActionBar
        actionBar!!.title = "Receive Materials"
        actionBar.setDisplayHomeAsUpEnabled(true)

        //set scanner text
        scanner_text.text = "Scan Material Barcode"

        //get quantity
        val quantity = intent?.extras?.getString(MaterialDetails.QUANTITY)
    }

    private fun codeScanner(){
        codeScanner = CodeScanner(this, scanner_view)

        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.SINGLE
            isAutoFocusEnabled = true
            isFlashEnabled = false

            decodeCallback = DecodeCallback {

                //Do database stuff


                //intent
                val intent = Intent(this@ReceiveMaterialsScanner, MaterialDetails::class.java)
                intent.putExtra(MaterialDetails.MATERIAL_ID, it.text)
                startActivity(intent)


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