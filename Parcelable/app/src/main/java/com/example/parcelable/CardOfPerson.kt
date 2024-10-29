package com.example.parcelable

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardOfPerson : AppCompatActivity() {

    private lateinit var enteredLastNameTV: TextView
    private lateinit var enteredAddressTV: TextView
    private lateinit var enteredPhoneTV: TextView

    @SuppressLint("NewApi", "StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card_of_person)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        enteredLastNameTV = findViewById(R.id.enteredLastNameTV)
        enteredAddressTV = findViewById(R.id.enteredAddressTV)
        enteredPhoneTV = findViewById(R.id.enteredPhoneTV)

        val person = intent.getParcelableExtra("PERSON", Person::class.java)
        enteredLastNameTV.text =
            getString(R.string.full_name, person?.lastName ?: "", person?.firstName ?: "")
        enteredAddressTV.text = HtmlCompat.fromHtml(
            getString(R.string.enteredAddress, person?.address.toString()),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
        enteredPhoneTV.text = HtmlCompat.fromHtml(
            getString(R.string.enteredPhone, person?.phone.toString()),
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }
}