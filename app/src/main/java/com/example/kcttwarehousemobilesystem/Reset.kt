package com.example.kcttwarehousemobilesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_reset.*

class Reset : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var currentPasswordEt: EditText
    private lateinit var newPasswordEt: EditText
    private lateinit var confirmPasswordEt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)
        auth = Firebase.auth

        btnChgPass.setOnClickListener {
            chgPassword()
        }
    }

    private fun chgPassword() {
        currentPasswordEt = findViewById(R.id.current_password_reset)
        newPasswordEt = findViewById(R.id.new_password_reset)
        confirmPasswordEt = findViewById(R.id.confirm_password_reset)

        var currentPassword: String = currentPasswordEt.text.toString()
        var newPassword: String = newPasswordEt.text.toString()

        if(currentPasswordEt.text.isNotEmpty() && newPasswordEt.text.isNotEmpty() && confirmPasswordEt.text.isNotEmpty()){

            if (newPasswordEt.text.toString().equals(confirmPasswordEt.text.toString())) {

                val user = auth.currentUser
                if(user != null && user.email != null) {
                    val credential = EmailAuthProvider
                            .getCredential(user.email!!, currentPassword)

// Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                            .addOnCompleteListener {
                                if(it.isSuccessful){
                                    Toast.makeText(this, "Re-Authentication success", Toast.LENGTH_SHORT).show()
                                    user!!.updatePassword(newPassword).addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Toast.makeText(this, "Password changed successfully", Toast.LENGTH_SHORT).show()
                                                    auth.signOut()
                                                    startActivity(Intent(this, Login::class.java))
                                                    finish()
                                                }
                                            }
                                }
                                else{
                                    Toast.makeText(this, "Re-Authentication failed", Toast.LENGTH_SHORT).show()
                                }
                            }
                }
                else {
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }
            }
            else {
                Toast.makeText(this, "Password mismatching.", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(this, "Please enter all the fields.", Toast.LENGTH_SHORT).show()
        }
    }
}