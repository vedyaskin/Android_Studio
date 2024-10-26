package com.example.lyfecycle_homework

import android.os.Bundle
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.round

class SecondActivity2 : AppCompatActivity() {
    private lateinit var myObserver: MyObserver
    private lateinit var textView: TextView
    private lateinit var imageView: ImageView
    private lateinit var scrollView: ScrollView
    private lateinit var largeTextView: TextView

    var bmi = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        scrollView = findViewById(R.id.scrollView)
        largeTextView = findViewById(R.id.largeTextView)

        myObserver = MyObserver()
        lifecycle.addObserver(myObserver)

        val weight = intent.getStringExtra("weight")?.toDouble()
        val height = intent.getStringExtra("height")?.toDouble()

        if (weight != null && height != null) {
            bmi = weight / (height * height)
            bmi = round(bmi * 100) / 100
            textView.text = getString(R.string.bmi) + bmi.toString()
        }
        if (bmi < 18.5) {
            imageView.setImageResource(R.drawable.slim)
            largeTextView.text = getString(R.string.slim)

        } else if (bmi >= 18.5 && bmi < 25.0) {
            imageView.setImageResource(R.drawable.normal)
            largeTextView.text = getString(R.string.normal)
        } else if (bmi >= 25.0 && bmi < 30) {
            imageView.setImageResource(R.drawable.fat)
            largeTextView.text = getString(R.string.fat)
        } else if (bmi >= 30) {
            imageView.setImageResource(R.drawable.very_fat)
            largeTextView.text = getString(R.string.very_fat)
        }
    }

}