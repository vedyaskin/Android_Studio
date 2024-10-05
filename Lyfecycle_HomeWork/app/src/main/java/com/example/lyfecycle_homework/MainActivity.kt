package com.example.lyfecycle_homework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttonBTN: Button
    private lateinit var weightET: EditText
    private lateinit var heightET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonBTN = findViewById(R.id.buttonBTN)
        weightET = findViewById(R.id.weightET)
        heightET = findViewById(R.id.heightET)

        buttonBTN.setOnClickListener {
            val intent = Intent(this, SecondActivity2::class.java)
            intent.putExtra("weight", weightET.text.toString())
            intent.putExtra("height", heightET.text.toString())
            if ( weightET.text.isNotEmpty() && heightET.text.isNotEmpty()){
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите данные", Toast.LENGTH_SHORT).show()
            }

        }
    }
}