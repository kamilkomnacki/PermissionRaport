package com.komnacki.permissionraport.easy_device_info.config

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyConfigPOJO : POJO {
    constructor()
    constructor(ringerMode : String) {
        this.ringerMode = ringerMode
    }

    @SerializedName("email")
    var ringerMode : String? = null

    override fun toString() : String {
        return ringerMode.toString()
    }
}