package com.komnacki.permissionraport

import android.Manifest
import android.app.Activity
import android.content.Context
import com.komnacki.permissionraport.contacts.Contacts
import com.komnacki.permissionraport.easy_device_info.baterry.EasyBattery
import com.komnacki.permissionraport.easy_device_info.bluetooth.EasyBluetooth
import com.komnacki.permissionraport.easy_device_info.config.EasyConfig
import com.komnacki.permissionraport.easy_device_info.device.EasyDevice
import com.komnacki.permissionraport.easy_device_info.id.EasyIds
import com.komnacki.permissionraport.easy_device_info.location.EasyLocation
import com.komnacki.permissionraport.easy_device_info.memory.EasyMemory
import com.komnacki.permissionraport.easy_device_info.network.EasyNetwork
import com.komnacki.permissionraport.easy_device_info.nfc.EasyNFC
import com.komnacki.permissionraport.easy_device_info.sim.EasySim
import com.komnacki.permissionraport.messages.Messages


class PermissionsList(val activity : Activity, val context : Context) {

    private var defaultStatus = false
    lateinit var PERMISSIONS_LIST : ArrayList<PermissionItem>

    init {
        PERMISSIONS_LIST = ArrayList(
            listOf(
                PermissionItem("Kontakty", listOf(Manifest.permission.READ_CONTACTS), defaultStatus, "", Contacts(activity), true),
                PermissionItem("Wiadomości", listOf(Manifest.permission.READ_SMS), defaultStatus, "", Messages(activity), true),
                PermissionItem("Podpięte konta email", listOf(Manifest.permission.GET_ACCOUNTS), defaultStatus, "", EasyIds(context), true),
                PermissionItem("", listOf(Manifest.permission.BATTERY_STATS), defaultStatus, "", EasyBattery(context), false),
                PermissionItem("", listOf(), defaultStatus, "", EasyDevice(activity, context), false),
                PermissionItem("", listOf(), defaultStatus, "", EasyConfig(context), false),
                PermissionItem(
                    "Sieć",
                    listOf(Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET),
                    defaultStatus,
                    "",
                    EasyNetwork(context),
                    true
                ),
                PermissionItem("", listOf(), defaultStatus, "", EasyMemory(context), false),
                PermissionItem("Bluetooth", listOf(Manifest.permission.BLUETOOTH), defaultStatus, "", EasyBluetooth(context), true),
                PermissionItem("", listOf(), defaultStatus, "", EasySim(context), false),
                PermissionItem(
                    "Lokalizacja",
                    listOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE
                    ),
                    defaultStatus,
                    "",
                    EasyLocation(activity, context),
                    true
                ),
                PermissionItem("", listOf(), defaultStatus, "", EasyNFC(context), false)

//                PermissionItem("Aparat", defaultStatus),
//                PermissionItem("Dyktafon", defaultStatus),
//                PermissionItem("Galeria", defaultStatus),
//                PermissionItem("Galeria2", defaultStatus),
//                PermissionItem("Galeria3", defaultStatus),
//                PermissionItem("Galeria4", defaultStatus)
            )
        )
    }

//    @TargetApi(Build.VERSION_CODES.M)
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 101){
//            for(int i=0;i&lt;grantResults.length;i++){
//                if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
//                    if(shouldShowRequestPermissionRationale(permissions[i])){
//                        new AlertDialog.Builder(this)
//                            .setMessage("Your error message here")
//                            .setPositiveButton("Allow", (dialog, which) -> requestMultiplePermissions())
//                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
//                        .create()
//                            .show();
//                    }
//                    return;
//                }
//            }
//            //all is good, continue flow
//        }
//    }

//    @TargetApi(Build.VERSION_CODES.M)
//    fun onRequestPermissionResult(requestCode : Int, permissions : Array<String>, grantResults : Array<Integer>){
//        if(requestCode == 101) {
//            for (i in 0..grantResults.size step 1) {
//                if (grantResults[i].toInt() != PackageManager.PERMISSION_GRANTED) {
//                    if(shouldShowRequestPermissionRationale(activity, permissions[i])) {
//                        AlertDialog.Builder(context)
//                            .setMessage("Error message")
//                            .setPositiveButton("Allow") { dialog, which -> requestMultiplePermissions()}
//                            .setNegativeButton("Cancel", {dialog, which -> dialog.dismiss()})
//                            .create()
//                            .show()
//                    }
//                    return
//                }
//            }
//        }
//    }

//    fun requestPermissions2() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (arePermissionsEnabled()) {
//                Toast.makeText(context, "PERMISSIONS GRANTED!", Toast.LENGTH_SHORT).show()
//                Log.d("MAIN: ", "permission granted")
//            } else {
//                Log.d("MAIN: ", "permission before request")
//                requestMultiplePermissions()
//                Log.d("MAIN: ", "permission after request")
//            }
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private fun arePermissionsEnabled() : Boolean {
//        for (permission in PERMISSIONS_LIST) {
//            if (checkSelfPermission(context, permission.manifest) != PackageManager.PERMISSION_GRANTED) {
//                return false
//            }
//        }
//        return true
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    fun requestMultiplePermissions() {
//        var remainingPermissions : MutableList<String> = ArrayList()
//        for (permission in PERMISSIONS_LIST) {
//            Log.d("MAIN: ", "permission: " + permission.name)
//            if (checkSelfPermission(context, permission.manifest) != PackageManager.PERMISSION_GRANTED) {
//                remainingPermissions.add(permission.manifest)
//            }
//        }
//        Log.d("MAIN: ", "list to grant: " + remainingPermissions.toString())
//
//
////        requestPermissions(remainingPermissions.toArray(new String [remainingPermissions.size()]), 101);
//        requestPermissions(activity, remainingPermissions.toTypedArray(), 101)
//    }
}