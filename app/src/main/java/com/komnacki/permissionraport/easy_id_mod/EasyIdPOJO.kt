package com.komnacki.permissionraport.easy_id_mod

import com.google.gson.annotations.SerializedName

class EasyIdPOJO {
    constructor()
    constructor(emails : String) {
        this.emails = emails
    }

    @SerializedName("emails")
    var emails : String? = null

    override fun toString() : String {
        return emails.toString()
    }
}