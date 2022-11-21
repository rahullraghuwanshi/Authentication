package com.rahulraghuwanshi.assignment.common

import android.app.Dialog
import android.content.Context
import android.util.Patterns
import android.view.Window
import android.widget.Toast
import com.rahulraghuwanshi.assignment.R

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}
fun Context.progressDialog(): Dialog {
    val dialog = Dialog(this)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.setCancelable(false)
    dialog.setContentView(R.layout.layout_custom_progress_dialog)
    return dialog
}

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
