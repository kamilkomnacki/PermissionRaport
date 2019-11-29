package com.komnacki.permissionraport.easy_device_info.sensor

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasySensorMod
import io.reactivex.Observable

class EasySensors : PojoFeeder {
    constructor(context : Context) {
        this.easyIdMod = EasySensorMod(context)
    }

    var easyIdMod : EasySensorMod? = null

    override fun getPOJO() : EasySensorsPOJO {
        return getSensorsInfo()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendSensorsInfo(email, getSensorsInfo())
    }

    private fun getSensorsInfo() : EasySensorsPOJO {
        return EasySensorsPOJO()
    }
}