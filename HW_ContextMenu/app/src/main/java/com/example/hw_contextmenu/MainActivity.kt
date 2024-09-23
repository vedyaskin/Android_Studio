package com.example.hw_contextmenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var gradeET: EditText
    private lateinit var generateBTN: Button
    private var random = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        gradeET = findViewById(R.id.gradeET)
        generateBTN = findViewById(R.id.generateBTN)
        registerForContextMenu(gradeET)
        generateBTN.setOnClickListener(this)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        try {

            var grade: Int = gradeET.text.toString().toInt()
            when (item.itemId) {
                R.id.item_color_quality -> colorQuality(grade)
                R.id.item_exit -> finish()
            }

        } catch (e: Exception) {
            alert()
        }
        return super.onContextItemSelected(item)
    }

    fun alert() {
        Toast.makeText(
            applicationContext,
            getString(R.string.exeption),
            Toast.LENGTH_LONG
        ).show()
        gradeET.text.clear()
        gradeET.setBackgroundColor(Color.WHITE)
    }

    fun colorQuality(grade: Int?) {
        if (!random) {
            when (grade) {
                1 -> gradeET.setBackgroundColor(getColor(R.color.orange))
                2 -> gradeET.setBackgroundColor(Color.YELLOW)
                3 -> gradeET.setBackgroundColor(Color.GREEN)
                4 -> gradeET.setBackgroundColor(Color.BLUE)
                5 -> gradeET.setBackgroundColor(Color.RED)
                else -> alert()
            }
        } else {
            when (grade) {
                in 1..10 -> gradeET.setBackgroundColor(Color.RED)
                in 11..20 -> gradeET.setBackgroundColor(getColor(R.color.orange))
                in 21..30 -> gradeET.setBackgroundColor(Color.YELLOW)
                in 31..40 -> gradeET.setBackgroundColor(Color.GREEN)
                in 41..50 -> gradeET.setBackgroundColor(Color.BLUE)
                else -> alert()
            }
        }
        random = false
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.generateBTN -> {
                random = true
                gradeET.setText(Random.nextInt(1, 50).toString())
            }
        }
    }
}