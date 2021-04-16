package com.example.kcttwarehousemobilesystem.UserFragment

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.User
import com.example.kcttwarehousemobilesystem.materialType.UserViewModel
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.fragment_register_acc.*
import kotlinx.android.synthetic.main.fragment_register_acc.view.*

class Register_acc : Fragment() {

    private lateinit var uUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_acc, container, false)

        uUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnSignUpFrag.setOnClickListener {
            val username = view.username_et.text.toString()
            val email =  view.email_et.text.toString()
            val password = view.password_ti.editText?.text.toString()
            val confirmPassword = view.confirmPassword_ti.editText?.text.toString()

            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)) {
                if(!isEmailValid(email)){
                    Toast.makeText(requireContext(), "Please fill in your valid email.", Toast.LENGTH_LONG).show()
                }
                else if(!TextUtils.equals(password, confirmPassword)){
                    Toast.makeText(requireContext(), "Please fill in correct confirm password.", Toast.LENGTH_LONG).show()
                }
                else {
                    // Create User Object
                    val user = User(0, username, email, password, confirmPassword)
                    // Add Data to Database
                    uUserViewModel.addUser(user)
                    Toast.makeText(requireContext(), "Successfully created!", Toast.LENGTH_LONG).show()
                    //Navigate Back
                    findNavController().navigate(R.id.action_register_acc_to_loginPage)
                }
            }
            else if(TextUtils.isEmpty(username)){
                Toast.makeText(requireContext(), "Please fill in your username.", Toast.LENGTH_LONG).show()
            }
            else if(TextUtils.isEmpty(email)){
                Toast.makeText(requireContext(), "Please fill in your email.", Toast.LENGTH_LONG).show()
            }
            else if(TextUtils.isEmpty(password)){
                Toast.makeText(requireContext(), "Please fill in your password.", Toast.LENGTH_LONG).show()
            }
            else if(TextUtils.isEmpty(confirmPassword)){
                Toast.makeText(requireContext(), "Please fill in the confirm password.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
            }
        }

        view.textBtnSignInFrag.setOnClickListener {
            findNavController().navigate(R.id.action_register_acc_to_loginPage)
        }
        return view
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}