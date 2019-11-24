package com.komnacki.permissionraport


import android.Manifest
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val PERMISSIONS_REQUEST_READ_CONTACTS = 1
    lateinit var sendPanel : SendPanel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        val rvAdapter = PermissionListAdapter(PermissionsUtils.PERMISSIONS_LIST)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvAdapter
        }

        var sendButton = SendPanel(applicationContext, findViewById(R.id.btn_send), findViewById(R.id.et_email))

//        setupSendButton()

        //todo: Sprawdzyć czy można to wywalić:
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        btn_send.setOnClickListener {
            Log.d("MAIN:", "btn click")
            Log.d("MAIN:", "positions checked:")
            Log.d("MAIN: ", "" + rvAdapter.getCheckedItems())

            val rxPermissions = RxPermissions(this)
            rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe { granted ->
                    if (granted) {
                        Log.d("MAIN", "Camera granted!")
                    } else {
                        Log.d("MAIN", "Camera not granted!")
                    }
                }

//            var disposables : CompositeDisposable = CompositeDisposable()
//            var disposables : MutableList<Disposable> = ArrayList()
//            rvAdapter.getCheckedItems().forEach {
                //                disposables.add()
//            }
            val service : PermissionsService = PermissionsService()
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

    private fun setupSendButton() {
        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(email_input : Editable?) {
            }

            override fun beforeTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
                //todo
            }

            override fun onTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
                //todo
            }

        })
    }

    private fun setSendButtonEnable(isEnable : Boolean) {
        val btn_sendData : Button = findViewById(R.id.btn_send)
        btn_sendData.isEnabled = isEnable
        if (isEnable) {
            btn_sendData.setTextColor(resources.getColor(R.color.colorWhite))
        } else {
            btn_sendData.setTextColor(resources.getColor(R.color.colorTextDisabled))
        }
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
//        val remainingPermissions = PermissionsUtils.PERMISSIONS_LIST_2.filter { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
//        requestPermissions(remainingPermissions.toTypedArray(), 101)
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    private fun arePermissionsEnabled() : Boolean {
//        return PermissionsUtils.PERMISSIONS_LIST_2.none { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
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
//        val c = com.komnacki.permissionraport.ContactsPOJO(contentResolver)
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