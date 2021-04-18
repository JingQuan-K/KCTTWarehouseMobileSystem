package com.example.kcttwarehousemobilesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_register_acc.view.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        txtBtnSignUpAc.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            finish()
        }

        btnLoginAc.setOnClickListener {
            doLogin()
        }

        txtBtnForgetPassAc.setOnClickListener {
            startActivity(Intent(this, ForgetPassword::class.java))
            finish()
        }

    }
    private fun doLogin(){
        val email =  email_login_et.text.toString()
        val password = password_login.text.toString()

        if(email_login_et.text.toString().isEmpty()){
            email_login_et.error = "Please enter your email"
            email_login_et.requestFocus()
            return
        }

        else if(!Patterns.EMAIL_ADDRESS.matcher(email_login_et.text.toString()).matches()){
            email_login_et.error = "Please enter a valid email"
            email_login_et.requestFocus()
            return
        }

        else if(password_login.text.toString().isEmpty()){
            password_login.error = "Please enter password"
            password_login.requestFocus()
            return
        }
        else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        updateUI(null)
                    }
                })
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?){
        if(currentUser!=null){
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
        }
    }
}