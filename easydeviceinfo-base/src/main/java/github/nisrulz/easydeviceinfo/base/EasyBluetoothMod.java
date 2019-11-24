/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.nisrulz.easydeviceinfo.base;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings.Secure;

import androidx.annotation.RequiresPermission;

/**
 * EasyBluetooth Mod Class
 */
public class EasyBluetoothMod {

    private final Context context;

    /**
     * Instantiates a new Easy bluetooth mod.
     *
     * @param context the context
     */
    public EasyBluetoothMod(Context context) {
        this.context = context;
    }

    /**
     * Gets Bluetooth MAC Address
     * <p>
     * You need to declare the below permission in the manifest file to use this properly
     * <p>
     * <uses-permission android:name="android.permission.BLUETOOTH"/>
     *
     * @return the bluetooth mac
     * @deprecated
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(permission.BLUETOOTH)
    @Deprecated
    public final String getBluetoothMAC() {
        String result = "00:00:00:00:00:00";
        if (PermissionUtil.hasPermission(this.context, permission.BLUETOOTH)) {
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)) {
                // Hardware ID are restricted in Android 6+
                // https://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-hardware-id
                // Getting bluetooth mac via reflection for devices with Android 6+
                result = Secure.getString(this.context.getContentResolver(), "bluetooth_address");
            } else {
                final BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
                result = (bta != null) ? bta.getAddress() : result;
            }
        }
        return CheckValidityUtil.checkValidData(result);
    }

    /**
     * Has Bluetooth LE
     *
     * @return true if the device has a Bluetooth LE compatible chipset
     */
    public final boolean hasBluetoothLe() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) && this.context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    /**
     * Has Bluetooth LE advertising
     *
     * @return true if the device has Bluetooth LE advertising features
     */
    @RequiresPermission(permission.BLUETOOTH)
    public final boolean hasBluetoothLeAdvertising() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) && this.hasBluetoothLe() && BluetoothAdapter.getDefaultAdapter().isMultipleAdvertisementSupported();
    }
}
