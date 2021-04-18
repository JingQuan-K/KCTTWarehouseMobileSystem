package com.example.kcttwarehousemobilesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        
        btnSignUp.setOnClickListener {
            signUpUser()
        }

        textBtnSignInAc.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

    private fun signUpUser(){
        if(editEmail.text.toString().isEmpty()){
            editEmail.error = "Please enter your email"
            editEmail.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(editEmail.text.toString()).matches()){
            editEmail.error = "Please enter a valid email"
            editEmail.requestFocus()
            return
        }

        if(password.text.toString().isEmpty()){
            password.error = "Please enter password"
            password.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(editEmail.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Sign Up Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, Login::class.java))
                        finish()
                    }
                    else {
                        Toast.makeText(this,"Sign Up Failed", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}