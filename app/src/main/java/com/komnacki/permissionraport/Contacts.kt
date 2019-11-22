package com.komnacki.permissionraport

import com.google.gson.annotations.SerializedName


class Contacts {
    @SerializedName("contacts")
    var data : Array<Contact>? = null

    override fun toString() : String {
        val string = StringBuilder()
        data !!.forEach { string.append(it.toString()) }
        return "Kontakty [contacts = $string]"
    }
}