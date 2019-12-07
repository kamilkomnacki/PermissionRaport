package com.komnacki.permissionraport.easy_device_info.sim

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasySimPOJO : POJO {
    constructor()
    constructor(
        simSerialNumber : String,
        country : String,
        carrier : String,
        isSimNetworkLocked : Int,
        isMultiSim : Int,
        numberOfActiveSim : Int,
        apiVersion : Int

    ) {
        this.simSerialNumber = simSerialNumber
        this.country = country
        this.carrier = carrier
        this.isSimNetworkLocked = isSimNetworkLocked
        this.isMultiSim = isMultiSim
        this.numberOfActiveSim = numberOfActiveSim
        this.apiVersion = apiVersion
    }

    @SerializedName("simSerialNumber")
    var simSerialNumber : String? = null

    @SerializedName("country")
    var country : String? = null

    @SerializedName("carrier")
    var carrier : String? = null

    @SerializedName("isSimNetworkLocked")
    var isSimNetworkLocked : Int? = null

    @SerializedName("isMultiSim")
    var isMultiSim : Int? = null

    @SerializedName("numberOfActiveSim")
    var numberOfActiveSim : Int? = null

    @SerializedName("apiVersion")
    var apiVersion : Int? = null

    override fun toString() : String {
        return "SIM: simSerialNumber: $simSerialNumber" +
                ", country: $country" +
                ", carrier: $carrier" +
                ", isSimNetworkLocked: $isSimNetworkLocked" +
                ", isMultiSim: $isMultiSim" +
                ", numberOfActiveSim: $numberOfActiveSim" +
                ", apiVersion: $apiVersion"
    }
}