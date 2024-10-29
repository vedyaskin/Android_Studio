package com.example.listview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var nameList = mutableListOf<User>()

    private lateinit var toolbar: Toolbar
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nameEditText = findViewById(R.id.nameET)
        ageEditText = findViewById(R.id.ageET)
        saveButton = findViewById(R.id.saveBTN)
        listView = findViewById(R.id.listView)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList)
        listView.adapter = adapter


        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            if (name.isNotEmpty() && age.isNotEmpty()) {
                if (age.toInt() <= 150){
                    nameList.add(User(name, age))
                    adapter.notifyDataSetChanged()
                    nameEditText.text.clear()
                    ageEditText.text.clear()
                } else {
                    Toast.makeText(
                        this,
                        "Введите корректный возраст",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                DeleteAlertDialog(this, nameList, adapter).show(position)

            }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_exit -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}