package com.example.parcelable

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var addressET: EditText
    private lateinit var phoneET: EditText
    private lateinit var btn: Button
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameET = findViewById(R.id.firstNameET)
        surnameET = findViewById(R.id.lastNameET)
        addressET = findViewById(R.id.addressET)
        phoneET = findViewById(R.id.phoneET)
        btn = findViewById(R.id.btn)
        listView = findViewById(R.id.listView)

        val listOfPerson = mutableListOf<Person>()
        val adapter = PersonAdapter(this, listOfPerson)

        listView.adapter = adapter

        btn.setOnClickListener {
            val personData = listOf(nameET, surnameET, addressET, phoneET)
            if (personData.all { it.text.toString().isNotBlank() }) {
                val name = nameET.text.toString()
                val surname = surnameET.text.toString()
                val address = addressET.text.toString()
                val phone = phoneET.text.toString()

                listOfPerson.add(Person(name, surname, address, phone))
                adapter.notifyDataSetChanged()
                nameET.text.clear()
                surnameET.text.clear()
                addressET.text.clear()
                phoneET.text.clear()
            }
        }
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as Person
            val intent = Intent(this, CardOfPerson::class.java)
            intent.putExtra("PERSON", selectedItem)
            startActivity(intent)
        }
    }
}