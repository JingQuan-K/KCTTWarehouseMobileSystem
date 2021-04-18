package com.example.kcttwarehousemobilesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
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

    }
    private fun doLogin(){
        val email =  email_login_et.text.toString()
        val password = password_login.text.toString()

        if(email_login_et.text.toString().isEmpty()){
            email_login_et.error = "Please enter your email"
            email_login_et.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_login_et.text.toString()).matches()){
            email_login_et.error = "Please enter a valid email"
            email_login_et.requestFocus()
            return
        }

        if(password_login.text.toString().isEmpty()){
            password_login.error = "Please enter password"
            password_login.requestFocus()
            return
        }

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser : FirebaseUser?){

    }
}