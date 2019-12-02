package com.komnacki.permissionraport.easy_device_info.baterry

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyBatteryMod
import io.reactivex.Observable

class EasyBattery : PojoFeeder {
    constructor(context : Context) {
        this.easyBatteryMod = EasyBatteryMod(context)
    }

    var easyBatteryMod : EasyBatteryMod? = null

    override fun getPOJO() : EasyBatteryPOJO {
        return EasyBatteryPOJO()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendBatteryState(email, getBatteryState())
    }

    private fun getBatteryState() : EasyBatteryPOJO {
        val value = easyBatteryMod !!.batteryPercentage
        val isCharge = easyBatteryMod !!.isDeviceCharging
        val technology = easyBatteryMod !!.batteryTechnology
        val temperature = easyBatteryMod !!.batteryTemperature
        val voltage = easyBatteryMod !!.batteryVoltage
        val hasBattery = easyBatteryMod !!.isBatteryPresent
        return EasyBatteryPOJO(value, isCharge, technology, temperature, voltage, hasBattery)
    }

}
