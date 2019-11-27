package com.komnacki.permissionraport.easy_device_info.baterry

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyBatteryPOJO : POJO {
    constructor()
    constructor(value : Int) {
        this.value = value
    }

    @SerializedName("value")
    var value : Int? = null

    override fun toString() : String {
        return value.toString()
    }
}
