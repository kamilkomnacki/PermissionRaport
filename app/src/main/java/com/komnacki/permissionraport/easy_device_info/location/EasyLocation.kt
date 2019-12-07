package com.komnacki.permissionraport.easy_device_info.location

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import com.patloew.rxlocation.RxLocation
import github.nisrulz.easydeviceinfo.base.EasyLocationMod
import io.reactivex.Observable
import io.reactivex.disposables.Disposable


class EasyLocation : PojoFeeder {
    constructor(activity : Activity, context : Context) {
        this.activity = activity
        this.context = context
        this.easyLocationMod = EasyLocationMod(context)
        this.locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

//        this.lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager?

        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        rxLocation = RxLocation(context)

//        var mGoogleApiClient : GoogleApiClient
//        Log.i("LOCATION", "Building GoogleApiClient")
//        mGoogleApiClient = GoogleApiClient.Builder(activity)
//            .addConnectionCallbacks(this)
//            .addOnConnectionFailedListener(this)
//            .addApi(LocationServices.API)
//            .build()
//        createLocationRequest()

    }

    private var country : String = ""
    var activity : Activity? = null
    var context : Context? = null
    val PERMISSION_ID = 42
    var easyLocationMod : EasyLocationMod? = null
    var fusedLocationClient : FusedLocationProviderClient? = null
    var rxLocation : RxLocation? = null
    var locationManager : LocationManager? = null
    var dispose : Disposable? = null


    override fun getPOJO() : POJO {
        return EasyLocationPOJO(country, country)
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
//        return getEasyLocation()
//            .concatMap { res -> service.sendLocationInfo(email, EasyLocationPOJO(res.longitude.toString(), res.countryName)) }


//        Observable.just(getEasyLocation())
//        Observable.just(getEasyLocation()).(service.sendLocationInfo(email, EasyLocationPOJO(country, country)))
//
//        return service.sendLocationInfo(email, getEasyLocation().sub)
        return service.sendLocationInfo(email, getEasyLocation())

    }

    fun getEasyLocation() : EasyLocationPOJO {

        try {
            var location = easyLocationMod !!.latLong
            Log.d("LOCATION", "l2: ${easyLocationMod !!.latLong[0]} , ${easyLocationMod !!.latLong[1]}")
            val longitude = location[0]
            val latitude = location[1]
            return EasyLocationPOJO(longitude.toString(), latitude.toString())

        } catch (e : SecurityException) {
            Log.d("EASY_LOCATION", e.toString())
            return EasyLocationPOJO("0", "0")
        }
//        return EasyLocationPOJO(easyLocationMod.latLong.toString())
        /*val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(3000)

        return rxLocation !!.location()
            .updates(locationRequest)
            .flatMap<Address> {
                    location -> rxLocation !!.geocoding().fromLocation(location).toObservable() }
*/

//        if (isLocationEnabled()) {
//            Log.d("LOCATION", "ENABLED!")
//            Log.d("LAST LOCATION", "below")
//            rxLocation !!.location()
//            getLastLocation()
//
//        }
//        val locationRequest = LocationRequest.create()
//            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//            .setInterval(5000)
//
//        var disposable = rxLocation !!.location().updates(locationRequest)
//            .flatMap<Address> { location ->
//                rxLocation !!
//                    .geocoding()
//                    .fromLocation(location)
//                    .toObservable()
//            }
//            .subscribe( { address ->
//                Log.d("LOCATION: ", address.countryName)
//            },
//            { error ->
//                Log.d("LOCATION: ", error.message)
//            })

//        return EasyLocationPOJO("0.0", "0.0")


//        fusedLocationClient !!.lastLocation
//            .addOnSuccessListener { location->
//                if (location != null) {
//                    val longitude = location !!.longitude
//                    val latitude = location.latitude
//                    Log.d("LOCATION", "l1: $longitude , $latitude")
//                    l = DoubleArray(2){latitude}
//                    // use your location object
//                    // get latitude , longitude and other info from this
//                } else {
//                    Log.d("LOCATION", "l2: 0.0 , 0.0")
//                    l = DoubleArray(2) { - 1.0 }
//                }
//            }
//        return EasyLocationPOJO("0.0", "0.0")


    }

    /*
    @Synchronized
    protected fun buildGoogleApiClient() {

    }

    private fun isLocationEnabled() : Boolean {
        return locationManager !!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager !!.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getLastLocation() : Pair<String, String> {
        if (isLocationEnabled()) {
            Log.d("LOCATION", "is enabled")
            fusedLocationClient !!.lastLocation.addOnCompleteListener(this.activity !!) { task ->
                var location : Location? = task.result
                if (location == null) {
                    Log.d("LOCATION", "is null, start new data")
                    requestNewLocationData()
                } else {
                    Log.d("LOCATION", "is not null")
                    Log.d("LOCATION1", location.latitude.toString())
                    Log.d("LOCATION2", location.longitude.toString())
                    return Pair<String, String>(location.latitude.toString(), location.longitude.toString())
                }
            }
            fusedLocationClient.on
        } else {
            Log.d("LOCATION", "is not enabled")
            Toast.makeText(context, "Turn on location", Toast.LENGTH_LONG).show()
            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(this.context !!, intent, Bundle())
        }
    }

    private fun requestNewLocationData() {
        Log.d("LOCATION", "requestNewLocationData start")
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.context !!)
        fusedLocationClient!!.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
        Log.d("LOCATION", "requestNewLocationData end")
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: Location = locationResult.lastLocation
            Log.d("LOCATION11", mLastLocation.latitude.toString())
            Log.d("LOCATION22", mLastLocation.longitude.toString())
        }
    }

     */

}