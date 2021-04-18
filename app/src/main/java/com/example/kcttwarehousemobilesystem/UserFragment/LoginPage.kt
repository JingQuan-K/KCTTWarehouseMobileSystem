package com.example.kcttwarehousemobilesystem.UserFragment

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
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
import com.example.kcttwarehousemobilesystem.materialType.UserViewModel
import kotlinx.android.synthetic.main.fragment_login_page.view.*
import kotlinx.android.synthetic.main.fragment_register_acc.view.*

class LoginPage : Fragment() {

    private lateinit var uUserViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_page, container, false)

        uUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.btnLoginFrag.setOnClickListener {
            val username = view.usernameLogin_et.text.toString()
            val password = view.password_login_ti.editText?.text.toString()

            if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty((password))){
                /*if(!validUser(username, password)){
                    Toast.makeText(requireContext(), "Incorrect username or password.", Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(requireContext(), "Login Successfully.", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.action_LoginPage_to_homePage)
                }*/
            }
            else if(TextUtils.isEmpty(username)){
                Toast.makeText(requireContext(), "Please fill in your username.", Toast.LENGTH_LONG).show()
            }
            else if(TextUtils.isEmpty(password)){
                Toast.makeText(requireContext(), "Please fill in your password.", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
            }
        }

        view.txtBtnSignUpFrag.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_register_acc)
        }

        view.txtBtnForgetPassFrag.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_forget_password)
        }
        return view
    }

/*    private fun validUser(username: String, password: String): Boolean{
        val writableDatabase
        val db: SQLiteDatabase = writableDatabase
        val db = writableDatabase
        val query = "select * from User where username = $username and password = $password"
        val cursor =
        return true
    }*/
}