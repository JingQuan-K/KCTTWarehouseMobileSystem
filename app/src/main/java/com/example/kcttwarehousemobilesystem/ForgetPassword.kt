package com.example.kcttwarehousemobilesystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPassword : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        auth = FirebaseAuth.getInstance()

        btnResetAc.setOnClickListener {
            val email = findViewById<EditText>(R.id.editEmailForgot)
            forgotPassword(email)
        }
    }

    private fun forgotPassword(email : EditText){
        if (email.text.toString().isEmpty()) {
            email.error = "Please enter your email"
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter a valid email"
            return
        }

        auth.sendPasswordResetEmail(email.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Email sent.",Toast.LENGTH_SHORT).show()
                }
            }
    }

}