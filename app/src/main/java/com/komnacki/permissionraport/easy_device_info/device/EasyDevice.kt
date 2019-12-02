package com.komnacki.permissionraport.easy_device_info.device

import android.app.Activity
import android.content.Context
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import github.nisrulz.easydeviceinfo.base.DeviceType
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod
import github.nisrulz.easydeviceinfo.base.OrientationType
import github.nisrulz.easydeviceinfo.base.PhoneType
import io.reactivex.Observable

class EasyDevice : PojoFeeder {
    constructor(activity : Activity, context : Context) {
        this.activity = activity
        this.easyDeviceMod = EasyDeviceMod(context)
    }

    var easyDeviceMod : EasyDeviceMod? = null
    var activity : Activity? = null

    override fun getPOJO() : POJO {
        return getDeviceInfo()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendDeviceInfo(email, getDeviceInfo())
    }

    private fun getDeviceInfo() : EasyDevicePOJO {
        val deviceType = easyDeviceMod !!.getDeviceType(activity)
        val deviceTypeName : String
        deviceTypeName = when (deviceType) {
            DeviceType.WATCH -> "Watch"
            DeviceType.PHONE -> "Telefon"
            DeviceType.PHABLET -> "Phablet"
            DeviceType.TABLET -> "Tablet"
            DeviceType.TV -> "TV"
            else -> "Nieznany"
        }

        val phoneType = easyDeviceMod !!.phoneType
        val phoneTypeName : String
        phoneTypeName = when (phoneType) {
            PhoneType.CDMA -> "CDMA"
            PhoneType.GSM -> "GSM"
            PhoneType.NONE -> "Phablet"
            else -> "Nieznany"
        }

        val orientationType = easyDeviceMod !!.getOrientation(activity)
        val orientationTypeName : String
        orientationTypeName = when (orientationType) {
            OrientationType.LANDSCAPE -> "Poziomy"
            OrientationType.PORTRAIT -> "Pionowy"
            OrientationType.UNKNOWN -> "Nieznany"
            else -> "Nieznany"
        }

        var imei : String
        var phoneNumber : String
        try {
            imei = easyDeviceMod !!.imei
            phoneNumber = easyDeviceMod !!.phoneNo
        } catch (e : SecurityException) {
            imei = "Nieznane"
            phoneNumber = "Nieznany"
        }

        return EasyDevicePOJO(
            imei,
            easyDeviceMod !!.buildVersionSDK,
            easyDeviceMod !!.manufacturer,
            easyDeviceMod !!.model,
            easyDeviceMod !!.osVersion,
            phoneNumber,
            easyDeviceMod !!.product,
            easyDeviceMod !!.device,
            easyDeviceMod !!.fingerprint,
            easyDeviceMod !!.isDeviceRooted,
            deviceTypeName,
            phoneTypeName,
            orientationTypeName
        )
    }
}