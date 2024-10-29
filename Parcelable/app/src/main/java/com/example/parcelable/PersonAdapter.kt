package com.example.parcelable

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PersonAdapter(context: Context, private val persons: List<Person>):
    ArrayAdapter<Person>(context, android.R.layout.simple_list_item_1, persons ){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view as TextView
        textView.text = persons[position].getFullName()
        return view
    }
}