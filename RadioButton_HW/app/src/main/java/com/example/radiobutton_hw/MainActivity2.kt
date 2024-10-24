package com.example.radiobutton_hw

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var groznyRB: RadioButton
    private var score = 0
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
        val intent = Intent(this, MainActivity3::class.java)

        radioGroup.setOnCheckedChangeListener { group, checkedID ->
            val radio: RadioButton = findViewById(checkedID)
            if (radio == groznyRB) {
                score += 100
            }
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }
    }
}