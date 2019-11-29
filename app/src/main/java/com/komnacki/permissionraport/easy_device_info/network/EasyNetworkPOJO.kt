package com.komnacki.permissionraport.easy_device_info.network

import com.google.gson.annotations.SerializedName
import com.komnacki.permissionraport.POJO

class EasyNetworkPOJO : POJO {
    constructor()
    constructor(
        isNetworkAvailable : Int,
        isWifiEnabled : Int,
        ipv4Address : String,
        ipv6Address : String,
        wifiSSID : String,
        wifiLinkSpeed : String,
        wifiBSSID : String,
        wifiMAC : String,
        networkType : String
    ) {
        this.isNetworkAvailable = isNetworkAvailable
        this.isWifiEnabled = isWifiEnabled
        this.ipv4Address = ipv4Address
        this.ipv6Address = ipv6Address
        this.wifiSSID = wifiSSID
        this.wifiLinkSpeed = wifiLinkSpeed
        this.wifiBSSID = wifiBSSID
        this.wifiMAC = wifiMAC
        this.networkType = networkType
    }

    @SerializedName("isNetworkAvailable")
    private var isNetworkAvailable : Int? = null

    @SerializedName("isWifiEnabled")
    private var isWifiEnabled : Int? = null

    @SerializedName("ipv4Address")
    private var ipv4Address : String? = null

    @SerializedName("ipv6Address")
    private var ipv6Address : String? = null

    @SerializedName("wifiSSID")
    private var wifiSSID : String? = null

    @SerializedName("wifiLinkSpeed")
    private var wifiLinkSpeed : String? = null

    @SerializedName("wifiBSSID")
    private var wifiBSSID : String? = null

    @SerializedName("wifiMAC")
    private var wifiMAC : String? = null

    @SerializedName("networkType")
    private var networkType : String? = null

    override fun toString() : String {
        return "[isNetworkAvailable: $isNetworkAvailable, isWifiEnabled: $isWifiEnabled, ipv4Address: $ipv4Address," +
                " ipv6Address: $ipv6Address, wifiSSID: $wifiSSID, wifiLinkSpeed: $wifiLinkSpeed, wifiBSSID: $wifiBSSID," +
                "wifiMAC: $wifiMAC, networkType: $networkType"
    }
}