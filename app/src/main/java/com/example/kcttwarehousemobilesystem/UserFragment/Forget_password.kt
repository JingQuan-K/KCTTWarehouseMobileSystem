package com.example.kcttwarehousemobilesystem.UserFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kcttwarehousemobilesystem.R
import kotlinx.android.synthetic.main.fragment_forget_password.*
import kotlinx.android.synthetic.main.fragment_forget_password.view.*

class Forget_password : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_forget_password, container, false)

        view.btnSendFrag.setOnClickListener {
            findNavController().navigate(R.id.action_ForgetPasswordPage_to_forget_password_cont)
        }
        return view
    }
}