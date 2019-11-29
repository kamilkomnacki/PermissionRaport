package com.komnacki.permissionraport

class PermissionItem(
    val name : String,
    val manifest : List<String>,
    var isChecked : Boolean,
    val data : String,
    val objectClass : PojoFeeder,
    val visibleOnList : Boolean
) {
    override fun toString() : String {
        return name
    }
}
