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

class MainActivity6 : AppCompatActivity() {
    private lateinit var  radioGroup6: RadioGroup
    private lateinit var stalinRB: RadioButton
    private lateinit var scoreInAct6TV: TextView

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

        var score = intent.getIntExtra("SCORE", 0)
        scoreInAct6TV.text = getString(R.string.score, score)

        val intent = Intent(this, ResultActivity::class.java)

        radioGroup6.setOnCheckedChangeListener{group, checkID ->
            val radio: RadioButton = findViewById(checkID)
            if ( radio == stalinRB ){
                score += 100
            }
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }
    }
}