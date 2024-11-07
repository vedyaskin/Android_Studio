package com.example.gridlayouttablelayout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolBarMain: Toolbar
    private lateinit var inputET: EditText
    private lateinit var resultTV: TextView
    private lateinit var resetBTN: Button

    private lateinit var button: Array<Button>

    private var listOfOperands: MutableList<String> = mutableListOf()
    private var operand = ""

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolBarMain = findViewById(R.id.toolBarMain)
        setSupportActionBar(findViewById(R.id.toolBarMain))

        inputET = findViewById(R.id.inputET)
        resultTV = findViewById(R.id.resultTV)
        resetBTN = findViewById(R.id.resetBTN)
        button = arrayOf(
            findViewById(R.id._0BTN),
            findViewById(R.id._1BTN),
            findViewById(R.id._2BTN),
            findViewById(R.id._3BTN),
            findViewById(R.id._4BTN),
            findViewById(R.id._5BTN),
            findViewById(R.id._6BTN),
            findViewById(R.id._7BTN),
            findViewById(R.id._8BTN),
            findViewById(R.id._9BTN),
            findViewById(R.id.multBTN),
            findViewById(R.id.plusBTN),
            findViewById(R.id.minusBTN),
            findViewById(R.id.divBTN),
            findViewById(R.id.resultBTN)
        )
        for (i in button) {
            i.setOnClickListener {
                inputET.isEnabled = false
                when {
                    i.text.isDigitsOnly() -> {
                        if (inputET.text.contains("=")) {
                            inputET.text.clear()
                        }
                        resultTV.text = ""
                        operand += i.text
                        inputET.append(i.text)
                    }

                    i.id != R.id.resultBTN -> {
                        if (inputET.text.contains("=")) {
                            inputET.text.clear()
                        }
                        addOperand()
                        addOperator(i.text.toString())
                    }

                    i.id == R.id.resultBTN -> {
                        addOperand()
                        inputET.append(" ${i.text}")
                        resultTV.append(calculation(listOfOperands).toString())
                        listOfOperands.clear()
                    }
                }
            }// button.setOnClickListener
        }// for (i in button)

        resetBTN.setOnClickListener {
            inputET.text.clear()
            resultTV.text = ""
            listOfOperands.clear()
            operand = ""
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("KEY_RESULT", resultTV.text.toString())
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        resultTV.text = savedInstanceState.getString("KEY_RESULT")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exit -> {
                MyDialog(this, this).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun setText(operator: String) {
        val currentText = inputET.text.toString()
        inputET.setText(currentText.dropLast(2) + operator + " ")
    }

    private fun addOperand() {
        if (operand != "") {
            listOfOperands.add(operand)
        }
        operand = ""
    }

    private fun addOperator(operator: String) {

        when {
            // если список не пуст и последний элемент - число,
            // то добавляем оператор в список
            listOfOperands.lastOrNull()?.toIntOrNull() != null -> {
                listOfOperands.add(operator)
                inputET.append(" $operator ")
            }
            // если список не пуст и последний элемент - не число (другой оператор)
            //
            (listOfOperands.isNotEmpty() && listOfOperands.last().toIntOrNull() == null) -> {

                when {
                    // вводимый оператор не равен "-", то меняем последний оператор на введенный
                    operator != "-" -> {
                        listOfOperands[listOfOperands.lastIndex] = operator
                        setText(operator)
                    }

                    // если вводимый оператор равен "-", а последний элемент списка равен "+",
                    // то меняем "+" на "-"
                    operator == "-" -> {
                        if (listOfOperands.last() == "+") {
                            listOfOperands[listOfOperands.lastIndex] = operator
                            setText(operator)

                            // в противном случае добавляем знак "-" операнду (т.е. делаем его отрицательным)
                        } else if (listOfOperands.last() != "-") {
                            operand += "-"
                            inputET.append(operand)
                        }
                    }
                }
            }
            listOfOperands.isEmpty() -> {

                if (operator == "-") {
                    operand += "-"
                    inputET.append(operand)
                }
            }
        }
    }

    private fun calculation(expression: MutableList<String>): Double {
        var result = 0.00
        while (expression.contains("*") || expression.contains("/")) {
            if (expression.contains("*")) {
                val idxOp = expression.indexOf("*")
                expression[idxOp] =
                    (expression[idxOp - 1].toDouble() * expression[idxOp + 1].toDouble()).toString()
                expression.removeAt(idxOp - 1)
                expression.removeAt(idxOp)
            }
            if (expression.contains("/")) {
                val idxOp = expression.indexOf("/")
                expression[idxOp] =
                    (expression[idxOp - 1].toDouble() / expression[idxOp + 1].toDouble()).toString()
                expression.removeAt(idxOp - 1)
                expression.removeAt(idxOp)
            }
        }
        if (expression[0].toIntOrNull() != null || expression[0].toDoubleOrNull() != null) {
            result = expression[0].toDouble()
        }
        for (i in 0..expression.lastIndex) {
            when (expression[i]) {
                "-" -> result -= expression[i + 1].toDouble()
                "+" -> result += expression[i + 1].toDouble()
            }
        }
        return result
    }
}


