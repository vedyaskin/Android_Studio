package com.example.radiobutton_hw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity3 : AppCompatActivity() {

    private lateinit var scoreTV: TextView
    private lateinit var radioGroup3: RadioGroup
    private lateinit var wowRB: RadioButton


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        radioGroup3 = findViewById(R.id.radioGroup3)
        scoreTV = findViewById(R.id.scoreTV)
        wowRB = findViewById(R.id.wowRB)
        var score = intent.getIntExtra("SCORE", 0)

        scoreTV.text = getString(R.string.score, score)

        val intent = Intent(this, MainActivity4::class.java)


        radioGroup3.setOnCheckedChangeListener { group, checkId ->
            val radio: RadioButton = findViewById(checkId)
            if ( radio == wowRB){
                score += 100
            }
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }
    }
}