package com.komnacki.permissionraport

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class ProgressDialog(context : Context) {
    private var progressDialog : Dialog
    private var title : TextView
    private var description : TextView
    private var progressBar : ProgressBar
    private var cancelButton : Button

    init {
        progressDialog = Dialog(context)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setCancelable(false)

        title = progressDialog.findViewById(R.id.dialog_title)
        title.text = "Przetwarzanie danych"

        description = progressDialog.findViewById(R.id.dialog_progress_description)
        description.text = "opis..."

        progressBar = progressDialog.findViewById(R.id.dialog_determinateBar)
        progressBar.progress = 55

        cancelButton = progressDialog.findViewById(R.id.dialog_btn_cancel)
        cancelButton.setOnClickListener {
            progressDialog.dismiss()
        }
    }

    fun show() {
        progressDialog.show()
    }

    fun hide() {
        progressDialog.hide()
    }

    fun setProgress(value : Int) {
        progressBar.progress = value
    }
}