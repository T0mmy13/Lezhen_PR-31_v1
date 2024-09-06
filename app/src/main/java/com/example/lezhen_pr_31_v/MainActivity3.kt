package com.example.lezhen_pr_31_v

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast


class MainActivity3 : AppCompatActivity() {

    private lateinit var spiner: Spinner
    private lateinit var image: ImageView
    private lateinit var selectedItem: String
    private lateinit var buton: Button
    private lateinit var Nums: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        spiner = findViewById(R.id.spinner)
        image = findViewById(R.id.Image)
        buton = findViewById(R.id.button)
        Nums = findViewById(R.id.editTextNums)
        sharedPreferences = getSharedPreferences("MyResultPref", MODE_PRIVATE)

        val items = arrayOf("Треугольник", "Круг", "Прямоугольник")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spiner.adapter = adapter
        spiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedItem = parent.getItemAtPosition(position) as String
                if (selectedItem == "Треугольник") {
                    image.setImageResource(R.drawable.triangle)
                    Nums.setHint("10 15 20")

                } else if (selectedItem == "Круг") {
                    image.setImageResource(R.drawable.circle)
                    Nums.setHint("10")
                }
                else if (selectedItem == "Прямоугольник"){
                    image.setImageResource(R.drawable.tetrahedral)
                    Nums.setHint("15 20")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        buton.setOnClickListener() {
            if (selectedItem == "Треугольник" && FindThreeNums(Nums.text.toString()) != null) {

                //val Res = FindThreeNums(Nums.text.toString())
                //Toast.makeText(this, "Треугольник ок", Toast.LENGTH_SHORT).show()

                SafeAndStart(selectedItem, "${FindThreeNums(Nums.text.toString())?.sum()}")
            }
            else if (selectedItem == "Круг" && FindOneNum(Nums.text.toString()) != null){

                //val Res = FindOneNum(Nums.text.toString())
                //Toast.makeText(this, "Круг ок", Toast.LENGTH_SHORT).show()

                SafeAndStart(selectedItem, "${(Nums.text.toString()).toInt() / 2*Math.PI}")
            }
            else if (selectedItem == "Прямоугольник" && FindTwoNums(Nums.text.toString()) != null){
                SafeAndStart(selectedItem, "${(Nums.text.toString()).toInt() * 2}")
            }
            else{
                Toast.makeText(this, "Вы некорректно ввели данные", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun SafeAndStart(select : String, Nums : String){
        val editor = sharedPreferences.edit()
        editor.putString("Figure", select)
        editor.putString("Nums", Nums)
        editor.commit()
        val intent = Intent(this, MainActivity4::class.java)
        startActivity(intent)
    }

    private fun FindThreeNums(input: String): List<Int>? {
        val regex = Regex("^(\\d+) (\\d+) (\\d+)$")
        val Result = regex.find(input)
        return Result?.destructured?.toList()?.map {
            it.toInt()
        }
    }
    private fun FindTwoNums(input: String): List<Int>? {
        val regex = Regex("^(\\d+) (\\d+)$")
        val Result = regex.find(input)
        return Result?.destructured?.toList()?.map {
            it.toInt()
        }
    }
    private fun FindOneNum(input: String): List<Int>? {
        val regex = Regex("^(\\d+)$")
        val Result = regex.find(input)
        return Result?.destructured?.toList()?.map {
            it.toInt()
        }
    }
}