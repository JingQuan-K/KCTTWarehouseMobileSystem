package com.example.kcttwarehousemobilesystem

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var emailEt: EditText
    private lateinit var passwordEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth

        emailEt = findViewById(R.id.email_login_et)
        passwordEt = findViewById(R.id.password_login)

/*        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }*/

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
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this, "Sign In Successfully", Toast.LENGTH_SHORT).show()
                        val user = auth.currentUser
                        updateUI(user)
                        /*Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()*/
                    } else {
                        // If sign in fails, display a message to the user.
                        /* updateUI(null)*/
                        Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser!=null){

// Below  if statement is added to check if email is verified
            if(currentUser.isEmailVerified) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else{
                Toast.makeText(this, "Please verify your email address", Toast.LENGTH_SHORT).show()
            }
        }
    }

//Add onStart method to auto sign in next time when app is launched
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
}