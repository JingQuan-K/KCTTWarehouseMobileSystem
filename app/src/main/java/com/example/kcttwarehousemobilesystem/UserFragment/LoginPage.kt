package com.example.kcttwarehousemobilesystem.UserFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import kotlinx.android.synthetic.main.fragment_login_page.view.*

class LoginPage : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login_page, container, false)

        view.btnLoginFrag.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_register_acc)
        }

        view.txtBtnSignUpFrag.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_register_acc)
        }

        view.txtBtnForgetPassFrag.setOnClickListener {
            findNavController().navigate(R.id.action_loginPage_to_forget_password)
        }
        return view
    }
}