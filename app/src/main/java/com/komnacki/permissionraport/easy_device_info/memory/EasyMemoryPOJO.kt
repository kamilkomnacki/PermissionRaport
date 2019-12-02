package com.komnacki.permissionraport.easy_device_info.memory

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyMemoryPOJO : POJO {
    constructor()
    constructor(
        totalRAM : Float,
        availableInternal : Float,
        availableExternal : Float,
        totalInternal : Float,
        totalExternal : Float
    ) {
        this.totalRAM = totalRAM
        this.availableInternal = availableInternal
        this.availableExternal = availableExternal
        this.totalInternal = totalInternal
        this.totalExternal = totalExternal
    }

    @SerializedName("totalRAM")
    var totalRAM : Float? = null

    @SerializedName("availableInternal")
    var availableInternal : Float? = null

    @SerializedName("availableExternal")
    var availableExternal : Float? = null

    @SerializedName("totalInternal")
    var totalInternal : Float? = null

    @SerializedName("totalExternal")
    var totalExternal : Float? = null

    override fun toString() : String {
        return "Memory: totalRAM: $totalRAM," +
                " availableInternal: $availableInternal," +
                " availableExternal: $availableExternal," +
                " totalInternal: $totalInternal" +
                " totalExternal: $totalExternal"
    }
}