package com.komnacki.permissionraport.easy_device_info.baterry

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyBatteryMod
import io.reactivex.Observable

class EasyBattery : PojoFeeder {
    constructor(context : Context) {
        this.easyBatteryMod = EasyBatteryMod(context)
    }

    var easyBatteryMod : EasyBatteryMod? = null

    override fun getPOJO() : Observable<out POJO> {
        return Observable.just(EasyBatteryPOJO())
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendBatteryState(email, getBatteryState())
    }

    private fun getBatteryState() : EasyBatteryPOJO {
        var value = easyBatteryMod?.batteryPercentage
        if (value != null) {
            return EasyBatteryPOJO(value)
        } else {
            return EasyBatteryPOJO(- 1)
        }
    }

}
