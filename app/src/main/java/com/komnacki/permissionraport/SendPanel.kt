package com.komnacki.permissionraport

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class SendPanel(context : Context, private var btn_view : Button, et_email : EditText) : TextWatcher {
    init {
        et_email.addTextChangedListener(this)
    }

    private var colorDisabled : Int = context.resources.getColor(R.color.colorTextDisabled)
    private var colorEnabled : Int = context.resources.getColor(R.color.colorWhite)

    override fun afterTextChanged(email_input : Editable?) {
        setSendButtonEnable(validateEmail(email_input.toString()))
    }

    override fun beforeTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
        //todo
    }

    override fun onTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
        //todo
    }

    fun setSendButtonEnable(isEnable : Boolean) {
        btn_view.isEnabled = isEnable
        if (true) {
            btn_view.setTextColor(colorEnabled)
        } else {
            btn_view.setTextColor(colorDisabled)
        }
    }

    private fun validateEmail(email : String) : Boolean {
        if (email.isEmpty() || email.isBlank()) {
            return false
        }

        if (email.length < Constants.MINIMUM_EMAIL_LENGTH) {
            return false
        }

        if (! (email.contains("@", false) && email.contains(".", false))) {
            return false
        }

        if (! (email[0].isLetterOrDigit() && email.last().isLetter())) {
            return false
        }

        if (email.count { ch -> ch == '@' } > 1) {
            return false
        }

        if (email.contains("..")) {
            return false
        }

        if (email.indexOf('@') + 1 >= email.indexOf('.', email.indexOf('@'))) {
            return false
        }

        return true
    }
}