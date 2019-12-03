package com.komnacki.permissionraport.easy_device_info.nfc

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyNFCPOJO : POJO {
    constructor()
    constructor(
        isNFCPresent : Boolean,
        isNFCEnable : Boolean
    ) {
        this.isNFCPresent = isNFCPresent
        this.isNFCEnable = isNFCEnable
    }

    @SerializedName("isNFCPresent")
    private var isNFCPresent : Boolean? = null

    @SerializedName("isNFCEnable")
    private var isNFCEnable : Boolean? = null

    override fun toString() : String {
        return "NFC: isPresent: $isNFCPresent, isEnable: $isNFCEnable"
    }
}
