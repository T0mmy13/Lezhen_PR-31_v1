package com.example.lezhen_pr_31_v

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var editTextLogin: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var button: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextLogin = findViewById(R.id.editTextLogin)
        editTextPassword = findViewById(R.id.editTextPassword)
        button = findViewById(R.id.button)
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        loadSavedData()
        button.setOnClickListener {
            validateAndProceed()
        }
    }

    private fun loadSavedData() {
        val savedLogin = sharedPreferences.getString("login", "")
        val savedPassword = sharedPreferences.getString("password", "")
        editTextLogin.setText(savedLogin)
        editTextPassword.setText(savedPassword)
    }
    private fun validateAndProceed() {
        val login = editTextLogin.text.toString().trim()
        val password = editTextPassword.text.toString().trim()

        if (login.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()
        }
        else if (login != "ects" || password != "ects2023"){
            Toast.makeText(this, "Неверный логин и/или пароль", Toast.LENGTH_SHORT).show()
        }
        else {
            saveLoginData(login, password)
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
    private fun saveLoginData(login: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("login", login)
        editor.putString("password", password)
        editor.apply()
    }
}