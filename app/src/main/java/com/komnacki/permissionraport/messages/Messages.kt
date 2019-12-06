package com.komnacki.permissionraport.messages

import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import com.komnacki.permissionraport.ApiResponse
import com.komnacki.permissionraport.POJO
import com.komnacki.permissionraport.PermissionsService
import com.komnacki.permissionraport.PojoFeeder
import io.reactivex.Observable

class Messages : PojoFeeder {
    constructor(activity : Activity) {
        this.contentResolver = activity.contentResolver
    }

    var contentResolver : ContentResolver? = null

    override fun getPOJO() : POJO {
        return getMessages()
    }

    override fun sendPOJO(service : PermissionsService, email : String) : Observable<ApiResponse> {
        return service.sendMessages(email, getMessages())
    }

    private fun getMessages() : MessagesPOJO {
        var list = ArrayList<MessagePOJO>()
        val cursor = contentResolver !!.query(Uri.parse("content://sms/inbox"), null, null, null, null)
        var counter = 0
        if (cursor !!.moveToFirst()) { // must check the result to prevent exception
            while (cursor.moveToNext()) {
                if (counter < 20) {
                    counter ++
                    Log.d("MESSAGE", cursor.toString())
                    var msgData = ""

//                    list.add(MessagePOJO(
//                        if (cursor.isNull(0)) - 1 else cursor.getLong(0),
//                        if (cursor.isNull(1)) - 1 else cursor.getLong(1),
//                        if (cursor.isNull(2)) "Brak danych" else cursor.getString(2),
//                        if (cursor.isNull(3)) "Brak danych" else cursor.getString(3),
//                        if (cursor.isNull(4)) - 1 else cursor.getLong(4),
//                        if (cursor.isNull(5)) - 1 else cursor.getLong(5),
//                        if (cursor.isNull(6)) - 1 else cursor.getLong(6),
//                        if (cursor.isNull(7)) "Brak danych" else cursor.getString(7),
//                        if (cursor.isNull(8)) "Brak danych" else cursor.getString(8),
//                        if (cursor.isNull(9)) "Brak danych" else cursor.getString(9),
//                        if (cursor.isNull(10)) "Brak danych" else cursor.getString(10),
//                        if (cursor.isNull(11)) "Brak danych" else cursor.getString(11)
//                    ))

                    //Przesuniecie, bo wybieram nie wszystkie pola
                    list.add(
                        MessagePOJO(
                            if (cursor.isNull(0)) - 1 else cursor.getLong(0),
                            if (cursor.isNull(1)) - 1 else cursor.getLong(1),
                            if (cursor.isNull(2)) "Brak danych" else cursor.getString(2),
                            if (cursor.isNull(3)) "Brak danych" else cursor.getString(3),
                            if (cursor.isNull(4)) - 1 else cursor.getLong(4),
                            if (cursor.isNull(5)) - 1 else cursor.getLong(5),
                            if (cursor.isNull(6)) - 1 else cursor.getLong(6),
                            if (cursor.isNull(7)) "Brak danych" else cursor.getString(7),
                            if (cursor.isNull(8)) "Brak danych" else cursor.getString(8),
                            if (cursor.isNull(9)) "Brak danych" else cursor.getString(9),
                            if (cursor.isNull(11)) "Brak danych" else cursor.getString(11),
                            if (cursor.isNull(12)) "Brak danych" else cursor.getString(12)
                        )
                    )

//                    var id : Long = if (cursor.isNull(0)) - 1 else cursor.getLong(0)
//                    var threadId : Long = if (cursor.isNull(1)) - 1 else cursor.getLong(1)
//                    var addressNumber : String = if (cursor.isNull(2)) "Brak danych" else cursor.getString(2)
//                    var person : String = if (cursor.isNull(3)) "Brak danych" else cursor.getString(3)
//                    var date : Long = if (cursor.isNull(4)) - 1 else cursor.getLong(4)
//                    var sendDate : Long = if (cursor.isNull(5)) - 1 else cursor.getLong(5)
//                    var protocol : Long = if (cursor.isNull(6)) - 1 else cursor.getLong(6)
//                    var read : String = if (cursor.isNull(7)) "Brak danych" else cursor.getString(7)
//                    var status : String = if (cursor.isNull(8)) "Brak danych" else cursor.getString(8)
//                    var type : String = if (cursor.isNull(9)) "Brak danych" else cursor.getString(9)
//                    var subject : String = if (cursor.isNull(10)) "Brak danych" else cursor.getString(10)
//                    var body : String = if (cursor.isNull(11)) "Brak danych" else cursor.getString(11)
//                    Log.d("MESSAGE1",
//                        id.toString() + ", " +
//                                threadId.toString() + ", " +
//                                addressNumber.toString() + ", " +
//                                person.toString() + ", " +
//                                person.toString() + ", " +
//                                date.toString() + ", " +
//                                sendDate.toString() + ", " +
//                                protocol.toString() + ", " +
//                                read.toString() + ", " +
//                                status.toString() + ", " +
//                                type.toString() + ", " +
//                                subject.toString() + ", " +
//                                body.toString())
//                    list.add(MessagePOJO(
//                        id,
//                        threadId,
//                        addressNumber,
//                        person,
//                        date,
//                        sendDate,
//                        protocol,
//                        read,
//                        status,
//                        type,
//                        subject,
//                        body
//                    ))

                    for (idx in 0 until cursor.columnCount) {

                        msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx)
                    }
                    Log.d("MESSAGE2", "msg: $msgData")


                } else {
                    break
                }
            }
        } else {
        }
        cursor.close()
        return MessagesPOJO(list)
    }
}