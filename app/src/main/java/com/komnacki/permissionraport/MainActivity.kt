package com.komnacki.permissionraport

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG : String = "MAIN"
    }

    lateinit var sendPanel : SendPanel
    lateinit var progressDialog : ProgressDialog

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionsList = PermissionsList(this, applicationContext)
        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        val rvAdapter = PermissionListAdapter(permissionsList.PERMISSIONS_LIST.filter { item -> item.visibleOnList })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvAdapter
        }

        sendPanel = SendPanel(applicationContext, findViewById(R.id.btn_send), findViewById(R.id.et_email))
        progressDialog = ProgressDialog(this)


        //todo: Sprawdzyć czy można to wywalić:
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        btn_send.setOnClickListener {

            Log.d(TAG, "send click")
            if (rvAdapter.getCheckedItems().isNullOrEmpty()) {
                Log.d(TAG, "No permissions selected!")
                showCheckOnePermissionAlert()
            } else {
                Log.d(TAG, "positions checked:")

                var arrayOfCheckedPermissions : Array<String> = rvAdapter.getCheckedItems().flatMap { per -> per.manifest }.toTypedArray()
                Log.d(TAG, "checked permissions: $arrayOfCheckedPermissions")

                val rxPermissions = RxPermissions(this)
                var disposable = rxPermissions
                    .request(*arrayOfCheckedPermissions)
                    .subscribe { granted ->
                        if (granted) {
                            val list : ArrayList<PermissionItem> = rvAdapter.getCheckedItems() as ArrayList<PermissionItem>
                            list.addAll(permissionsList.PERMISSIONS_LIST.filter { per -> ! per.visibleOnList })
                            handleData(list.map { item -> item.objectClass })
                        } else {
                            Log.d(TAG, "Permissions not granted!")
                            showPermissonsNotAcceptedAlert()
                        }
//                        progressDialog.hide()
                    }

//            var disposables : CompositeDisposable = CompositeDisposable()
//            var disposables : MutableList<Disposable> = ArrayList()
//            rvAdapter.getCheckedItems().forEach {
                //                disposables.add()
//            }
//            service.getContacts("wapnpoland@gmail.com")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                    { message ->
//                        Log.d("Main: ", message.toString())
//                    },
//                    { error ->
//                        Log.d("Main Error", error.toString())
//                    }
//                )

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                if (arePermissionsEnabled()) {
//                    Log.d("MAIN: ", "Permission granted")
//                                        permissions granted, continue flow normally
//                    var easyId = EasyId(applicationContext)
//                    Log.d("Main", "emails: ${easyId.getUserEmails()}")
//                } else {
//                    Log.d("MAIN: ", "requestMultiplePermissions")
//                    requestMultiplePermissions()
//                }
//            } else {
//                requestMultiplePermissions()
//            }
            }
        }
    }

    private fun handleData(arrayOfCheckedPermissions : List<PojoFeeder>) {
        progressDialog.show()
        val service : PermissionsService = PermissionsService()
        var pojos = ArrayList<Observable<ApiResponse>>()
        Log.d(TAG, "size of arrayOfCheckedPermissions: ${arrayOfCheckedPermissions.size}")
        arrayOfCheckedPermissions.forEach { it ->
            Log.d(TAG, "per: $it")
            pojos.add(it.sendPOJO(service, sendPanel.getEmail()))
        }

        val allRequestsCount = pojos.size
        var correctRequestsCount = 0
        progressDialog.setProgress(0)

        Log.d(TAG, "start disposable")
        var disposable = Observable.concat(pojos)
            .concatWith(sendRaport(service))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate { progressDialog.hide() }
            .subscribe(
                { result ->
                    Log.d(TAG, "A: " + result.response)
                    val progress : Int = ((correctRequestsCount ++ / allRequestsCount.toFloat()) * 100).roundToInt()
                    progressDialog.setProgress(progress)
                },
                { error ->
                    Log.d(TAG, error.message)
                    showSendErrorAlert(error.message)
                }
            )
    }

    private fun sendRaport(service : PermissionsService) : Observable<ApiResponse> {
        Log.d(TAG, "SEND RAPORT!")
        return service.sendRaport(sendPanel.getEmail())
    }

    private fun showSendErrorAlert(error : String?) {
        showDialogWithOK(
            "Błąd!",
            "Wystąpił błąd podczas przetwarzania danych. Spróbój ponownie.\nBłąd: $error"
        )
    }

    private fun showPermissonsNotAcceptedAlert() {
        showDialogWithOK(
            "Zaakceptuj wszystkie pozwolenia!",
            "Nie wszystkie pozwolenia ,które zostały zaznaczone, zostały zaakceptowane. Upewnij się że pozwalasz aplikacji na wykorzystanie zaznaczonych zasobów i spróbój ponownie.\n\nJeśli aplikacja nie wyświetla prośby o udostępnienie zasobów urządzenia, sprawdź opcje aplikacji w ustawieniach systemu."
        )
    }

    private fun showCheckOnePermissionAlert() {
        showDialogWithOK("Zaznacz pozwolenie!", "Zaznacz co najmniej jedno pozwolenie, aby aplikacja mogła wygenerowac raport.")
    }

    private fun showDialogWithOK(title : String, message : String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setPositiveButton(android.R.string.ok, null)
        builder.setMessage(message)
        builder.show()
    }


    /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (arePermissionsEnabled()) {
            Log.d("MAIN: ", "Permission granted")
            //                    permissions granted, continue flow normally
            var c = Contacts(contentResolver)
            var con = ContactsPOJO(c.getContacts())
            Log.d("MAIN", "Contacts: ${c.getContacts()}")
            service.sendContacts("wapnpoland@gmail.com", con)
                .concatWith(service.sendRaport("wapnpoland@gmail.com"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        Log.d("MAIN: ", "contacts sent! $response")
                    },
                    { error ->
                        Log.d("MAIN: ", "error during sending contacts: $error")
                    }
                )
        } else {
            Log.d("MAIN: ", "requestMultiplePermissions")
            requestMultiplePermissions()
        }
    }
}
}*/

//    private fun getCheckedItems(rvAdapter : PermissionListAdapter) : List<PermissionItem> {
//        for (i in 0 until rvAdapter.itemCount) {
//            if (rvAdapter.isPermissionSelected(i)) {
//                Log.d("MAIN: ", "{$i} isChecked: " + rvAdapter.getItemName(i))
//            } else {
//                Log.d("MAIN: ", "{$i} isNotChecked: " + rvAdapter.getItemName(i))
//            }
//        }
//    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private fun requestMultiplePermissions() {
//        val remainingPermissions = PermissionsList.PERMISSIONS_LIST_2.filter { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
//        requestPermissions(remainingPermissions.toTypedArray(), 101)
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private fun arePermissionsEnabled() : Boolean {
//        return PermissionsList.PERMISSIONS_LIST_2.none { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
//    }
//
//    @TargetApi(Build.VERSION_CODES.M)
//    override fun onRequestPermissionsResult(
//        requestCode : Int, permissions : Array<String>,
//        grantResults : IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 101) {
//            if (grantResults.any { it != PackageManager.PERMISSION_GRANTED }) {
//                Log.d("MAIN: ", "There is permssions not granted!")
//                if (permissions.any { shouldShowRequestPermissionRationale(it) }) {
//                    Log.d("MAIN: ", "Permissions should show rationale!")
//                    AlertDialog.Builder(this)
//                        .setMessage("Your error message here")
//                        .setPositiveButton("Allow") { dialog, which -> requestMultiplePermissions() }
//                        .setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }
//                        .create()
//                        .show()
//                }
//            }
//            //all is good, continue flow
//            var c = Contacts(contentResolver)
//            Log.d("MAIN CONTACTS: ", c.getContacts().toString())
//        }
//    }


    ///=====================================================================

//    private fun requestPermission(permission : String) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val checkSelfPermission = ContextCompat.checkSelfPermission(this, permission)
//            if(checkSelfPermission != PackageManager.PERMISSION_GRANTED)
//
//        }
//    }


    //TODO: TO na dole jest ok:
//    fun requestContactPermission() {
//        val c = com.komnacki.permissionraport.contacts.ContactsPOJO(contentResolver)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val checkSelfPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
//            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
//                    val builder = AlertDialog.Builder(this)
//                    builder.setTitle("Read contacts access needed")
//                    builder.setPositiveButton(android.R.string.ok, null)
//                    builder.setMessage("Please enable access to contacts.")
//                    builder.setOnDismissListener(DialogInterface.OnDismissListener {
//                        requestPermissions(
//                            arrayOf(android.Manifest.permission.READ_CONTACTS),
//                            PERMISSIONS_REQUEST_READ_CONTACTS
//                        )
//                    })
//                    builder.show()
//                } else {
//                    ActivityCompat.requestPermissions(
//                        this,
//                        arrayOf(android.Manifest.permission.READ_CONTACTS),
//                        PERMISSIONS_REQUEST_READ_CONTACTS
//                    )
//                }
//            } else {
//                Log.d("MAIN: ", c.getContacts().toString())
//                for (contact in c.getContacts()) {
//                    sendToServer(contact)
//                }
//            }
//        } else {
//            Log.d("MAIN: ", c.getContacts().toString())
//            for (contact in c.getContacts()) {
//                sendToServer(contact)
//            }
//        }
//    }

}