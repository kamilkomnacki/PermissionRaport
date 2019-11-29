package com.komnacki.permissionraport.easy_device_info.network

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyNetworkMod
import github.nisrulz.easydeviceinfo.base.NetworkType
import io.reactivex.Observable

class EasyNetwork : PojoFeeder {
    constructor(context : Context) {
        this.easyNetworkMod = EasyNetworkMod(context)
    }

    var easyNetworkMod : EasyNetworkMod? = null

    override fun getPOJO() : EasyNetworkPOJO {
        return getNetworkInfo()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendNetworkInfo(email, getNetworkInfo())
    }

    private fun getNetworkInfo() : EasyNetworkPOJO {
        var networkType : String = "Niezidentyfikowany typ"
        when (easyNetworkMod !!.networkType) {
            NetworkType.CELLULAR_2G -> networkType = "2G"
            NetworkType.CELLULAR_3G -> networkType = "3G"
            NetworkType.CELLULAR_4G -> networkType = "4G"
            NetworkType.CELLULAR_UNIDENTIFIED_GEN -> networkType = "Niezidentyfikowany typ"
            NetworkType.WIFI_WIFIMAX -> networkType = "WIFI-MAX"
            NetworkType.UNKNOWN -> networkType = "Niezidentyfikowany typ"
        }
        return EasyNetworkPOJO(
            if (easyNetworkMod !!.isNetworkAvailable) 1 else 0,
            if (easyNetworkMod !!.isWifiEnabled) 1 else 0,
            easyNetworkMod !!.iPv4Address,
            easyNetworkMod !!.iPv6Address,
            easyNetworkMod !!.wifiSSID,
            easyNetworkMod !!.wifiLinkSpeed,
            easyNetworkMod !!.wifiBSSID,
            easyNetworkMod !!.wifiMAC,
            networkType
        )
    }
}