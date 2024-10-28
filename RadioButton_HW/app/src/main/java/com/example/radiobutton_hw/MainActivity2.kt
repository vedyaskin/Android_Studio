package com.example.radiobutton_hw

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var groznyRB: RadioButton
    private lateinit var nextBTN: Button
    private lateinit var rightAnswer: String
    private var score = 0
    private var selectedRadioButton: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        radioGroup = findViewById(R.id.radioGroup)
        groznyRB = findViewById(R.id.groznyRB)
        nextBTN = findViewById(R.id.nextBTN)
        rightAnswer = getString(R.string.ans1Q1)

        val intent = Intent(this, MainActivity3::class.java)

        radioGroup.setOnCheckedChangeListener { _, checkedID ->
            if (checkedID != -1) {
                selectedRadioButton = findViewById(checkedID)
                val selectedOption = selectedRadioButton?.text.toString()
                if (selectedOption == rightAnswer) {
                    score += 100
                }
            }
        }
        nextBTN.setOnClickListener {
            if (selectedRadioButton != null){
                intent.putExtra("SCORE", score)
                startActivity(intent)
                finish()
            } else{
                Toast.makeText(
                    applicationContext,
                    "Выберите ответ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}