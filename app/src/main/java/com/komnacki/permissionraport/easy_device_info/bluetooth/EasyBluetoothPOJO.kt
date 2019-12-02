package com.komnacki.permissionraport.easy_device_info.bluetooth

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyBluetoothPOJO : POJO {
    constructor()
    constructor(macAddress : String) {
        this.macAddress = macAddress
    }

    @SerializedName("macAddress")
    var macAddress : String? = null

    override fun toString() : String {
        return "Bluetooth: macAddress: $macAddress"
    }
}