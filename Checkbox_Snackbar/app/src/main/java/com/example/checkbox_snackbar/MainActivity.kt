package com.example.checkbox_snackbar

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var inputDataET: EditText
    private lateinit var dataViewTV: TextView
    private lateinit var saveBTN: Button
    private lateinit var delBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inputDataET = findViewById(R.id.input_DataET)
        dataViewTV = findViewById(R.id.textViewTV)
        saveBTN = findViewById(R.id.saveBTN)
        delBTN = findViewById(R.id.delBTN)

        saveBTN.setOnClickListener(this)
        delBTN.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
       when (p0?.id){
           R.id.saveBTN -> {
               dataViewTV.text = inputDataET.text
           }
           R.id.delBTN -> {
               val rootView = findViewById<View>(R.id.main)
               val snackbar = Snackbar.make(rootView, "Подтвердите удаление", Snackbar.LENGTH_LONG)
               snackbar.setAction("Удалить") {
                   dataViewTV.text = ""
                   inputDataET.text.clear()
                   Snackbar.make(rootView, "Данные удалены.", Snackbar.LENGTH_LONG).show()
               }
               snackbar.show()

           }
       }
    }
}