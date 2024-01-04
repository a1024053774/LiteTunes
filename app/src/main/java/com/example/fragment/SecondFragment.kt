package com.example.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment


class SecondFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val loginButton = view.findViewById<Button>(R.id.login_button)
        val emailInput = view.findViewById<EditText>(R.id.email_input)
        val passwordInput = view.findViewById<EditText>(R.id.password_input)

        val sharedPref = activity?.getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val registeredEmail = sharedPref?.getString("email", "")
        val registeredPassword = sharedPref?.getString("password", "")


        loginButton.setOnClickListener {
            val inputEmail = emailInput.text.toString()
            val inputPassword = passwordInput.text.toString()
            if (registeredEmail != null)
            {
            if(inputEmail == registeredEmail && inputPassword == registeredPassword)
            {
                Toast.makeText(activity, "登录成功", Toast.LENGTH_SHORT).show()
                startActivity(Intent(activity, ThirdActivity::class.java))
            }else{
                Toast.makeText(activity, "邮箱或密码错误", Toast.LENGTH_SHORT).show()
            }
                }
            }

        return view

    }

}