package com.example.lezhen_pr_31_v

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var result : TextView
    private lateinit var figure : TextView
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        result = findViewById(R.id.editTextResult)
        figure = findViewById(R.id.editTextFigure)
        sharedPreferences = getSharedPreferences("MyResultPref", MODE_PRIVATE)
        var FiguretString = sharedPreferences.getString("Figure", "nothing")
        val ResultString = sharedPreferences.getString("Nums", "nothing")
        figure.setText(FiguretString)
        result.setText(ResultString)

        button = findViewById(R.id.button)

        button.setOnClickListener(){
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }
}