package com.komnacki.permissionraport.easy_device_info.location

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyLocationPOJO : POJO {
    constructor()
    constructor(
        lat : String,
        lon : String
    ) {
        this.lat = lat
        this.lon = lon
    }

    @SerializedName("lat")
    var lat : String? = null

    @SerializedName("lon")
    var lon : String? = null

    override fun toString() : String {
        return "Location: lan: $lat, lon: $lon"
    }
}