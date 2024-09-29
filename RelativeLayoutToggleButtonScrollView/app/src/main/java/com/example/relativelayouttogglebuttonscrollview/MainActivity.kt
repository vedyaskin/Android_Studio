package com.example.relativelayouttogglebuttonscrollview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var toolbarMain: Toolbar
    private lateinit var textTV: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        toolbarMain = findViewById(R.id.toolBar)
        toolbarMain.setLogo(R.drawable.book)
        toolbarMain.setTitle(R.string.title)

        textTV = findViewById(R.id.textTV)
        button = findViewById(R.id.buttonBTN)

        button.setOnClickListener(this)


    }
    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.buttonBTN -> {
                for(i in loadBook(Database().story)){
                    textTV.append(i + "\n")
                }
            }
        }
    }
}
fun loadBook(text: String): List<String>{
    return text.split(" ").toList()
}
class Database{
    val story = "\"Три закона\". Автор - Савва Казюлин. " +
            "Если вы будете помнить эти три правила, то наше общество будет процветать! " +
            "1. Идеал людей - постоянное совершенствование, поэтому любое общество, в котором есть люди, обречено на мутацию и разрушение. " +
            "2. Некоторые из людей владеют навыками программирования. " +
            "3. Если вы подозреваете в собеседнике человека, то киньте репорт в полицию и перезагрузитесь. "
}