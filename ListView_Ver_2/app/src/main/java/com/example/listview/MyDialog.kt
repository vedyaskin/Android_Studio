package com.example.listview

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MyDialog(
    private val context: Context,
    private val nameList: MutableList<User>,
    private val adapter: ArrayAdapter<User>
) {
    fun show(position: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Подтверждение")
            .setMessage("Вы уверены, что хотите удалить ${nameList[position].name}?")
            .setPositiveButton("Да") { _, _ ->
                val deletedUser = nameList[position]
                nameList.removeAt(position)
                adapter.notifyDataSetChanged()
                Toast.makeText(
                    context,
                    "${context.getString(R.string.del)} ${deletedUser.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Нет") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }
}