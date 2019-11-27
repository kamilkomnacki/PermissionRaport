package com.komnacki.permissionraport.easy_device_info.id

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyIdPOJO : POJO {
    constructor()
    constructor(emails : String) {
        this.email = emails
    }

    @SerializedName("email")
    var email : String? = null

    override fun toString() : String {
        return email.toString()
    }
}