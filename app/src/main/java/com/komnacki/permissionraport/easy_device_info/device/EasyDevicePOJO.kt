package com.komnacki.permissionraport.easy_device_info.device

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyDevicePOJO : POJO {
    constructor()
    constructor(
        IMEI : String,
        SDK : Int,
        manufacturer : String,
        model : String,
        osVersion : String,
        phoneNumber : String,
        product : String,
        device : String,
        fingerprint : String,
        isRooted : Boolean,
        deviceType : String,
        phoneType : String,
        orientationType : String
    ) {
        this.IMEI = IMEI
        this.SDK = SDK
        this.manufacturer = manufacturer
        this.model = model
        this.osVersion = osVersion
        this.phoneNumber = phoneNumber
        this.product = product
        this.device = device
        this.fingerprint = fingerprint
        this.isRooted = isRooted
        this.deviceType = deviceType
        this.phoneType = phoneType
        this.orientationType = orientationType
    }

    @SerializedName("IMEI")
    var IMEI : String? = null

    @SerializedName("SDK")
    var SDK : Int? = null

    @SerializedName("manufacturer")
    var manufacturer : String? = null

    @SerializedName("model")
    var model : String? = null

    @SerializedName("osVersion")
    var osVersion : String? = null

    @SerializedName("phoneNumber")
    var phoneNumber : String? = null

    @SerializedName("product")
    var product : String? = null

    @SerializedName("device")
    var device : String? = null

    @SerializedName("fingerprint")
    var fingerprint : String? = null

    @SerializedName("isRooted")
    var isRooted : Boolean? = null

    @SerializedName("deviceType")
    var deviceType : String? = null

    @SerializedName("phoneType")
    var phoneType : String? = null

    @SerializedName("orientationType")
    var orientationType : String? = null


    override fun toString() : String {
        return "device: IMEI: $IMEI" +
                " SDK: $SDK" +
                " manufacturer: $manufacturer" +
                " model: $model" +
                " osVersion: $osVersion" +
                " phoneNumber: $phoneNumber" +
                " product: $product" +
                " device: $device" +
                " fingerprint: $fingerprint" +
                " isRooted: $isRooted" +
                " deviceType: $deviceType" +
                " phoneType: $phoneType" +
                " orientationType: $orientationType"
    }
}
