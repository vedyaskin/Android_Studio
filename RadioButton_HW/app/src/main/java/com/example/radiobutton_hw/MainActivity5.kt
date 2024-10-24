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

class MainActivity5 : AppCompatActivity() {

    private lateinit var radioGroup5: RadioGroup
    private lateinit var kievRB: RadioButton
    private lateinit var scoreInAct5TV: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        radioGroup5 = findViewById(R.id.radioGroup5)
        kievRB = findViewById(R.id.kievRB)
        scoreInAct5TV = findViewById(R.id.scoreInAct5TV)

        var score = intent.getIntExtra("SCORE", 0)
        scoreInAct5TV.text = getString(R.string.score, score)

        val intent = Intent(this, MainActivity6::class.java)

        radioGroup5.setOnCheckedChangeListener{group, checkID ->
            val radio: RadioButton = findViewById(checkID)
            if (radio == kievRB){
                score += 100
            }
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }
    }
}