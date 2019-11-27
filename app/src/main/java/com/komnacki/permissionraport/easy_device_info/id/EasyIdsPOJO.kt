package com.komnacki.permissionraport

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.easy_device_info.id.EasyIdPOJO


class EasyIdsPOJO : POJO {
    @SerializedName("emails")
    var data : Array<EasyIdPOJO>? = null

    constructor()
    constructor(emails : List<String>) {
        data = Array(emails.size) { i ->
            EasyIdPOJO(emails[i])
        }
    }

    override fun toString() : String {
        val string = StringBuilder()
        data !!.forEach { string.append(it.toString()) }
        return "User emails: [emails = $string]"
    }

}