package com.komnacki.permissionraport

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.PermissionChecker.checkSelfPermission


class PermissionsUtils(val activity : Activity, val context : Context) {
    companion object {
        private var defaultStatus = false

        //        val PL : Triple<String, String, Boolean> = {
//
//        }
        val PERMISSIONS_LIST : ArrayList<PermissionItem> = ArrayList(
            listOf(
                PermissionItem("Kontakty", Manifest.permission.READ_CONTACTS, defaultStatus, ""),
                PermissionItem("Wiadomości", Manifest.permission.READ_SMS, defaultStatus, ""),
                PermissionItem("Pamięć urządzenia", Manifest.permission.READ_EXTERNAL_STORAGE, defaultStatus, "")
//                PermissionItem("Aparat", defaultStatus),
//                PermissionItem("Dyktafon", defaultStatus),
//                PermissionItem("Galeria", defaultStatus),
//                PermissionItem("Galeria2", defaultStatus),
//                PermissionItem("Galeria3", defaultStatus),
//                PermissionItem("Galeria4", defaultStatus)
            )
        )

        val PERMISSIONS_LIST_2 = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.GET_ACCOUNTS
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

    fun requestPermissions2() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (arePermissionsEnabled()) {
                Toast.makeText(context, "PERMISSIONS GRANTED!", Toast.LENGTH_SHORT).show()
                Log.d("MAIN: ", "permission granted")
            } else {
                Log.d("MAIN: ", "permission before request")
                requestMultiplePermissions()
                Log.d("MAIN: ", "permission after request")
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun arePermissionsEnabled() : Boolean {
        for (permission in PERMISSIONS_LIST) {
            if (checkSelfPermission(context, permission.manifest) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    fun requestMultiplePermissions() {
        var remainingPermissions : MutableList<String> = ArrayList()
        for (permission in PERMISSIONS_LIST) {
            Log.d("MAIN: ", "permission: " + permission.name)
            if (checkSelfPermission(context, permission.manifest) != PackageManager.PERMISSION_GRANTED) {
                remainingPermissions.add(permission.manifest)
            }
        }
        Log.d("MAIN: ", "list to grant: " + remainingPermissions.toString())


//        requestPermissions(remainingPermissions.toArray(new String [remainingPermissions.size()]), 101);
        requestPermissions(activity, remainingPermissions.toTypedArray(), 101)
    }
}