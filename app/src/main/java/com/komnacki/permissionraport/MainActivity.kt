package com.komnacki.permissionraport

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        var database = FirebaseDatabase.getInstance()
            .getReferenceFromUrl("https://permissionraport.firebaseio.com")

        btn_send.setOnClickListener {
            Log.d("MAIN:", "btn click")
            database.push().setValue(et_email.text.toString(), "addd")
//            database.child("test").child("1").setValue("Kamil")
//            finish()
        }
    }
}
