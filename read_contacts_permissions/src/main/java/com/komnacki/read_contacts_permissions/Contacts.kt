//package com.komnacki.read_contacts_permissions
//
//import android.content.ContentResolver
//import android.provider.ContactsContract
//import android.util.Log
//
//class Contacts(private val contentResolver : ContentResolver) {
//
//    //todo: make private
//    fun getContacts() : ArrayList<Contact> {
//        var contacts : ArrayList<Contact> = ArrayList()
//        var cursor = contentResolver.query(
//            ContactsContract.Contacts.CONTENT_URI,
//            null,
//            null,
//            null,
//            null
//        )
//
//        if (cursor != null && cursor.count > 0) {
//            while (cursor.moveToNext()) {
//                var id : Long = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID))
//                var name : String = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//                var phoneNumber : String = String()
//                var email : String = String()
//                if (cursor.getInt((cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
//                    var pCur = contentResolver.query(
//                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                        null,
//                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                        arrayOf(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))),
//                        null
//                    )
//
//                    while (pCur != null && pCur.moveToNext()) {
//                        phoneNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                        email = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
//
//                    }
//
//                    pCur !!.close()
//                }
//                contacts.add(Contact(id, name, phoneNumber, email))
//            }
//        }
//
//        Log.d("READ CONTACTS", contacts.toString())
//        return contacts
//    }
//}
