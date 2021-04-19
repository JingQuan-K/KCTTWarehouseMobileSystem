package com.example.kcttwarehousemobilesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth

        emailEt = findViewById(R.id.email_login_et)
        passwordEt = findViewById(R.id.password_login)

        btnSignUp.setOnClickListener {
            /*signUpUser()*/
            var email: String = emailEt.text.toString()
            var password: String = passwordEt.text.toString()

            if(TextUtils.isEmpty(email)){
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show()
            }

            else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            }

            else if(TextUtils.isEmpty(password)){
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
            }

            else {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

// Below three lines added for sending the authentication link to the user's email
                            auth.currentUser?.sendEmailVerification()
                                ?.addOnCompleteListener { task ->
                                    if (task.isSuccessful) {

                                        val user = auth.currentUser

// below message changed and user is navigated to Sign In activity
                                        Toast.makeText(this, "Sign Up Successfully. Verification link sent to the Email address", Toast.LENGTH_SHORT).show()
                                        startActivity(Intent(this, Login::class.java))
                                        finish()
                                    }
                                    else {
                                        Toast.makeText(this, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }
                    }
            }
        }

        textBtnSignInAc.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }

}