package com.example.gridlayouttablelayout

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog

class MyDialog (private val context: Context, private val activity: Activity) {
    fun show(){
        val builder = AlertDialog.Builder(context)
        builder.setMessage(context.getString(R.string.confirm_exit))
            .setPositiveButton(context.getString(R.string.yes)){ _, _ ->
                activity.finish()
            }
            .setNegativeButton(context.getString(R.string.no)){ dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}