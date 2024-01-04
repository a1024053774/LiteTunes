package com.example.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val emailInput = view.findViewById<EditText>(R.id.email_input)
        val passwordInput = view.findViewById<EditText>(R.id.password_input)
        val registerButton = view.findViewById<Button>(R.id.register_button)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val rb = view.findViewById<RadioButton>(checkedId)
            if (rb != null && checkedId > -1) {
                Toast.makeText(context, rb.text, Toast.LENGTH_SHORT).show()
            }
        }
        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()
            val sharedPref = activity?.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val editor = sharedPref?.edit()

            if (editor != null) {
                editor.putString("email", email)
            }
            if (editor != null) {
                editor.putString("password", password)
            }
            if (editor != null) {
                editor.apply()
            }

            val secondFragment = SecondFragment()
//            val bundle = Bundle()
//            bundle.putString("email", email)
//            bundle.putString("password", password)
//            secondFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.fragment_container, secondFragment)?.commit()
        }
        return view
    }

}

