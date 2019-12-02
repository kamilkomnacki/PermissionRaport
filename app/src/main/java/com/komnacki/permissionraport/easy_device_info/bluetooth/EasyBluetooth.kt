package com.komnacki.permissionraport.easy_device_info.bluetooth

import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.EasyBluetoothMod
import io.reactivex.Observable

class EasyBluetooth : PojoFeeder {
    constructor(context : Context) {
        this.easyBatteryMod = EasyBluetoothMod(context)
    }

    var easyBatteryMod : EasyBluetoothMod? = null

    override fun getPOJO() : POJO {
        return getBluetoothPOJO()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendBluetoothInfo(email, getBluetoothPOJO())
    }

    fun getBluetoothPOJO() : EasyBluetoothPOJO {
        return EasyBluetoothPOJO(easyBatteryMod !!.bluetoothMAC)
    }
}