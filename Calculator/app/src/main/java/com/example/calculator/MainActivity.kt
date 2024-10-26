package com.example.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar



class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var firstTimeET: EditText
    private lateinit var secondTimeET: EditText
    private lateinit var buttonSumBTN: Button
    private lateinit var buttonDifBTN: Button
    private lateinit var resultTV: TextView

    private lateinit var toolbarMain: Toolbar


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

        toolbarMain = findViewById(R.id.toolBarMain)
        setSupportActionBar(toolbarMain)
        title = getString(R.string.time_calc)
        toolbarMain.subtitle = "версия 1"
        toolbarMain.setLogo(R.drawable.ic_moretime)

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

        val rootView = findViewById<View>(R.id.main)
        val snackbar = Snackbar.make(rootView,"Результат: ${result}", Snackbar.LENGTH_LONG)
        snackbar.setBackgroundTint(ContextCompat.getColor(applicationContext, R.color.white));
        snackbar.setTextColor(Color.parseColor("#8B0000"))
        snackbar.show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.resetMenuMain -> {
                firstTimeET.text.clear()
                secondTimeET.text.clear()
                resultTV.text = ""
                resultTV.hint = getString(R.string.result)
                Toast.makeText(
                    applicationContext,
                    "Данные очищены",
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    "Приложение закрыто",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // перевод строки в секунды
    fun stringToSeconds(string: String): Int {
        val str = string.replace(" ", "").lowercase()
        var sec = 0
        var timeEntered = false
        try {
            if (str.contains("h")) {
                sec += str.substringBefore("h").toInt() * (60 * 60)
                timeEntered = true
            }
            if (str.contains("m")) {
                val temp = str.substring(str.indexOf("h") + 1, str.indexOf("m")).toInt()
                if (timeEntered && temp > 60) {
                    showAlertDialog(this, "Введено $temp мин. В часе не может быть столько.")
                } else {
                    sec += temp * (60)
                    timeEntered = true
                }
            }
            if (str.contains("s")) {
                val temp = str.substring(str.indexOf("m") + 1, str.indexOf("s")).toInt()

                if (timeEntered && temp > 60) {
                    showAlertDialog(this, "Введено $temp сек. В минуте не может быть столько.")
                } else {
                    sec += str.substring(str.indexOf("m") + 1, str.indexOf("s")).toInt()
                    timeEntered = true
                }
            }
            if (!timeEntered) {
                showAlertDialog(this, "$str")
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