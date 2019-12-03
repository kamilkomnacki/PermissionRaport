package com.komnacki.permissionraport.easy_device_info.location

import android.content.Context
import android.util.Log
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyLocationMod
import io.reactivex.Observable

class EasyLocation : PojoFeeder {
    constructor(context : Context) {
        this.easyLocationMod = EasyLocationMod(context)
    }

    var easyLocationMod : EasyLocationMod? = null

    override fun getPOJO() : POJO {
        return getEasyLocation()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendLocationInfo(email, getEasyLocation())
    }

    fun getEasyLocation() : EasyLocationPOJO {
        var location : DoubleArray
        try {
            location = easyLocationMod !!.latLong
        } catch (e : SecurityException) {
            Log.d("EASY_LOCATION", e.toString())
            location = DoubleArray(2) { - 1.0 }
        }
        return EasyLocationPOJO(location[0].toString(), location[1].toString())
    }
}