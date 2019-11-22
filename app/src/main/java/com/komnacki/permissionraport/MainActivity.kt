package com.komnacki.permissionraport

//import com.komnacki.read_contacts_permissions.Contact
//import com.komnacki.read_contacts_permissions.Contacts

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val PERMISSIONS_REQUEST_READ_CONTACTS = 1
    val MINIMUM_EMAIL_LENGHT = 5

    private val databaseReference : DatabaseReference
        get() {
            var database = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://permissionraport.firebaseio.com")
            return database
        }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        val rvAdapter = PermissionListAdapter(PermissionsUtils.PERMISSIONS_LIST)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvAdapter
        }


        val btn_sendData : Button = findViewById(R.id.btn_send)
        val et_email : EditText = findViewById(R.id.et_email)

        et_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0 : Editable?) {
                if (validateEmail(p0.toString())) {
                    btn_sendData.isEnabled = true
                    btn_sendData.setTextColor(resources.getColor(R.color.colorTextDisabled))
                } else {
                    btn_sendData.isEnabled = false
                    btn_sendData.setTextColor(resources.getColor(R.color.colorWhite))
                }
            }

            override fun beforeTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
                //todo
            }

            override fun onTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
                //todo
            }

        })


        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        btn_send.setOnClickListener {
            Log.d("MAIN:", "btn click")
            Log.d("MAIN:", "positions checked:")
            Log.d("MAIN: ", "" + rvAdapter.getCheckedItems())


            var service : Service = PermissionsService.retrofit.create(Service::class.java)
            var call : Observable<String> = service.helloWorld()

            call
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { message ->
                        Log.d("Main", message)
                    },
                    { error ->
                        Log.d("Main Error", error.toString())
                    }
                )


//            c.getContacts()
//            database.child("test").child("1").setValue("Kamil")
//            finish()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (arePermissionsEnabled()) {
                    Log.d("MAIN: ", "Permission granted")
                    //                    permissions granted, continue flow normally
                } else {
                    Log.d("MAIN: ", "requestMultiplePermissions")
                    requestMultiplePermissions()
                }
            }
//            requestContactPermission()
        }
    }

//    private fun getCheckedItems(rvAdapter : PermissionListAdapter) : List<PermissionItem> {
//        for (i in 0 until rvAdapter.itemCount) {
//            if (rvAdapter.isPermissionSelected(i)) {
//                Log.d("MAIN: ", "{$i} isChecked: " + rvAdapter.getItemName(i))
//            } else {
//                Log.d("MAIN: ", "{$i} isNotChecked: " + rvAdapter.getItemName(i))
//            }
//        }
//    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun arePermissionsEnabled() : Boolean {
        return PermissionsUtils.PERMISSIONS_LIST_2.none { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun requestMultiplePermissions() {
        val remainingPermissions = PermissionsUtils.PERMISSIONS_LIST_2.filter { checkSelfPermission(it) != PackageManager.PERMISSION_GRANTED }
        requestPermissions(remainingPermissions.toTypedArray(), 101)
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode : Int, permissions : Array<String>,
        grantResults : IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 101) {
            if (grantResults.any { it != PackageManager.PERMISSION_GRANTED }) {
                Log.d("MAIN: ", "There is permssions not granted!")
                if (permissions.any { shouldShowRequestPermissionRationale(it) }) {
                    Log.d("MAIN: ", "Permissions should show rationale!")
                    AlertDialog.Builder(this)
                        .setMessage("Your error message here")
                        .setPositiveButton("Allow") { dialog, which -> requestMultiplePermissions() }
                        .setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }
                        .create()
                        .show()
                }
            }
            //all is good, continue flow
        }
    }


    ///=====================================================================

//    private fun requestPermission(permission : String) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val checkSelfPermission = ContextCompat.checkSelfPermission(this, permission)
//            if(checkSelfPermission != PackageManager.PERMISSION_GRANTED)
//
//        }
//    }


    //TODO: TO na dole jest ok:
//    private fun sendToServer(c : Contact) {
//        var database = FirebaseDatabase.getInstance()
//            .getReferenceFromUrl("https://permissionraport.firebaseio.com")
//        database.push().setValue(c.toString(), "addd")
//    }
//
//    fun requestContactPermission() {
//        val c = Contacts(contentResolver)
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
//
//
    fun validateEmail(email : String) : Boolean {
//        if (email.isNullOrEmpty() || email.isBlank()) {
//            return false
//        }
//
//        if (email.length < MINIMUM_EMAIL_LENGHT) {
//            return false
//        }
//
//        if (! (email.contains("@", false) || email.contains(".", false))) {
//            return false
//        }
//
//        if (! (email[0].isLetterOrDigit() && email.last().isLetter())) {
//            return false
//        }

        return true
    }
}