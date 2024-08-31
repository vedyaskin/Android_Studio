package com.example.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var firstTimeET: EditText
    private lateinit var secondTimeET: EditText
    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDifBTN: Button
    private lateinit var resultTV: TextView

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
        firstTimeET = findViewById(R.id.timeFirstET)
        secondTimeET = findViewById(R.id.timeSecondET)
        buttonSumBTN = findViewById(R.id.SumBTN)
        buttonDifBTN = findViewById(R.id.DifBTN)
        resultTV = findViewById(R.id.resultTV)
        buttonDifBTN.setOnClickListener(this)
        buttonSumBTN.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var firstTime = stringToSeconds(firstTimeET.text.toString())
        var secondTime = stringToSeconds(secondTimeET.text.toString())

        var result = when (v?.id) {
            R.id.SumBTN -> {
                secondsToString(firstTime + secondTime)
            }

            R.id.DifBTN -> secondsToString(firstTime - secondTime)
            else -> 0
        }
        resultTV.text = result.toString()
    }

    // перевод строки в секунды
    fun stringToSeconds(string: String): Int {
        val str = string.replace(" ", "").lowercase()
        var sec = 0
        var hourOrMinEntered = false
        try {
            if (str.contains("h")) {
                sec += str.substringBefore("h").toInt() * (60 * 60)
                hourOrMinEntered = true
            }
            if (str.contains("m")) {
                val temp = str.substring(str.indexOf("h") + 1, str.indexOf("m")).toInt()
                if (hourOrMinEntered && temp > 60){
                    showAlertDialog(this, "Введено $temp мин. В часе не может быть столько.")
                } else {
                    sec += temp * (60)
                    hourOrMinEntered = true
                }
            }
            if (str.contains("s")) {
                val temp = str.substring(str.indexOf("m") + 1, str.indexOf("s")).toInt()
                if (hourOrMinEntered && temp > 60){
                    showAlertDialog(this, "Введено $temp сек. В минуте не может быть столько.")
                } else {
                    sec += str.substring(str.indexOf("m") + 1, str.indexOf("s")).toInt()
                }
            }
            return sec

        } catch (e: Exception) {
            showAlertDialog(this, "$string")
            return -1
        }
    }

    fun secondsToString(sec: Int): String {
        val hours = sec / 3600
        val min = (sec % 3600) / 60
        val sec = (sec % 3600) % 60
        val string = "${hours}h ${min}m ${sec}s"
        return string
    }

    fun showAlertDialog(context: Context, message: String) {
        Log.e("AlertDialog", message)
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Неправильный формат времени!")
            .setMessage(message)
            .setPositiveButton("ОК") { dialog, _ ->
                // Закрываем диалог
                dialog.dismiss()
            }

        // Создаем и отображаем диалог
        val alert = builder.create()
        alert.show()
    }
}