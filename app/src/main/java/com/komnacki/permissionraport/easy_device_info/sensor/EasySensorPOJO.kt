package com.komnacki.permissionraport.easy_device_info.sensor

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasySensorPOJO : POJO {
    constructor()
    constructor(name : String, vendorAndVersion : String, power : String, type : String, reportingMode : String) {
        this.name = name
        this.vendorAndVersion = vendorAndVersion
        this.power = power
        this.type = type
        this.reportingMode = reportingMode
    }

    @SerializedName("name")
    var name : String? = null

    @SerializedName("vendorAndVersion")
    var vendorAndVersion : String? = null

    @SerializedName("power")
    var power : String? = null

    @SerializedName("type")
    var type : String? = null

    @SerializedName("reportingMode")
    var reportingMode : String? = null

    override fun toString() : String {
        return "[$name, $vendorAndVersion, $power, $type, $reportingMode]"
    }
}