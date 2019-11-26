package com.komnacki.permissionraport.easy_device_info.id

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyIdPOJO : POJO {
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