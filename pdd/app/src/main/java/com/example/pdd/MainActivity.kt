package com.example.pdd

import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var bigTextView: TextView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textView = findViewById(R.id.textTV)
        checkBox = findViewById(R.id.checkbox)
        bigTextView = findViewById(R.id.pddTV)

        toolbar = findViewById(R.id.toolBarMain)
        setSupportActionBar(toolbar)

        title = getString(R.string.app_name)

        checkBox.setOnCheckedChangeListener {_, isChecked ->
            if (isChecked){
                textView.text = getString(R.string.pdd)
                bigTextView.text = getString(R.string.big_text)
            } else {
                textView.text = getString(R.string.info)
                bigTextView.text = ""
            }
        }
    }
}