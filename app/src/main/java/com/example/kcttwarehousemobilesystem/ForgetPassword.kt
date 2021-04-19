package com.example.kcttwarehousemobilesystem

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forget_password.*


class ForgetPassword : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        auth = Firebase.auth

        emailEt = findViewById(R.id.email_et)

        btnResetAc.setOnClickListener {
            var email: String = emailEt.text.toString()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            }
            else {
/*                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(this, OnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Reset link sent to your email", Toast.LENGTH_LONG)
                                        .show()
                            } else {
                                Toast.makeText(this, "Unable to send reset mail", Toast.LENGTH_LONG)
                                        .show()
                            }
                        })*/
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user =auth.currentUser
                            Toast.makeText(this,"We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this,"Failed to send reset email!", Toast.LENGTH_SHORT).show()
                        }
                    }
                /*forgotPassword(email)*/
            }
    }

    /*private fun forgotPassword(email : EditText){
        emailEt = findViewById(R.id.email_et)

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
            }*/
    }

}