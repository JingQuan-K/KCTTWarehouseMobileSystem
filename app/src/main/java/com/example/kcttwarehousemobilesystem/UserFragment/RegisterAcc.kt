package com.example.kcttwarehousemobilesystem.UserFragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import com.example.kcttwarehousemobilesystem.entity.User
import com.example.kcttwarehousemobilesystem.entity.UserViewModel
import kotlinx.android.synthetic.main.fragment_register_acc.*
import kotlinx.android.synthetic.main.fragment_register_acc.view.*

class RegisterAcc : Fragment() {

    private lateinit var uUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register_acc, container, false)

        uUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnSignUpFrag.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val username = username_et.text.toString()
        val email =  email_et.text.toString()
        val password = password_ti.editText?.text.toString()
        val confirmPassword = confirmPassword_ti.editText?.text.toString()

        if(inputCheck(username, email, password, confirmPassword)) {
            // Create User Object
            val user = User(0, username, email, password, confirmPassword)
            // Add Data to Database
            uUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully created!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_registerAcc_to_loginPage)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(username: String, email: String, password: String, confirmPassword: String): Boolean{
        return !(TextUtils.isEmpty(username) && TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confirmPassword))
    }
}