package com.example.radiobutton_hw

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    private lateinit var resultTV: TextView
    private lateinit var gradeTV: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main7)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gradeTV = findViewById(R.id.gradeTV)
        resultTV = findViewById(R.id.resultTV)
        val score = intent.getIntExtra("SCORE", 0)
        resultTV.text = getString(R.string.scored, score)

        when (score) {
            0 -> gradeTV.text = getString(R.string.subzero)
            in 100..200 -> gradeTV.text = getString(R.string.newbie)
            in 300..400 -> gradeTV.text = getString(R.string.good)
            500 -> gradeTV.text = getString(R.string.perfect)
        }
    }
}