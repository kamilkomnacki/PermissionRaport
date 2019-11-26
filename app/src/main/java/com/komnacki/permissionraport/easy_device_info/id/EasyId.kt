package com.komnacki.permissionraport.easy_device_info.id

import android.content.Context
import github.nisrulz.easydeviceinfo.base.EasyIdMod

class EasyId() {
    constructor(context : Context) : this() {
        this.easyIdMod = EasyIdMod(context)
    }

    var easyIdMod : EasyIdMod? = null

    fun getUserEmails() : EasyIdPOJO {
        //Get Google Email ID
        val emailIds = easyIdMod?.accounts
        val emailString = StringBuilder()
        if (emailIds != null && emailIds.isNotEmpty()) {
            for (e in emailIds) {
                emailString.append(e).append("\n")
            }
        } else {
            emailString.append("Nie znaleziono")
        }

        return EasyIdPOJO(emailString.toString())
    }
}