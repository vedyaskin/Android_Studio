package com.example.radiobutton_hw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity6 : AppCompatActivity() {
    private lateinit var  radioGroup6: RadioGroup
    private lateinit var stalinRB: RadioButton
    private lateinit var scoreInAct6TV: TextView
    private lateinit var nextBTN: Button
    private lateinit var rightAnswer: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main6)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        radioGroup6 = findViewById(R.id.radioGroup6)
        stalinRB = findViewById(R.id.stalinRB)
        scoreInAct6TV =findViewById(R.id.scoreInAct6TV)
        nextBTN = findViewById(R.id.nextBTN)
        rightAnswer = getString(R.string.ans1Q5)

        var score = intent.getIntExtra("SCORE", 0)
        scoreInAct6TV.text = getString(R.string.score, score)

        val intent = Intent(this, ResultActivity::class.java)

        radioGroup6.setOnCheckedChangeListener { _, checkedID ->
            if (checkedID != -1) {
                val selectedRadioButton = findViewById<RadioButton>(checkedID)
                val selectedOption = selectedRadioButton.text.toString()
                if (selectedOption == rightAnswer) {
                    score += 100
                }
            }
        }
        nextBTN.setOnClickListener {
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }
    }
}