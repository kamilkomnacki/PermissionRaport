package com.komnacki.permissionraport

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PermissionListAdapter(val list : List<PermissionItem>) :
    RecyclerView.Adapter<PermissionListAdapter.PermissionListViewHolder>() {
    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : PermissionListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_permission, parent, false)

        return PermissionListViewHolder(view)
    }

    override fun getItemCount() : Int {
        return list.size
    }

    override fun onBindViewHolder(holder : PermissionListViewHolder, position : Int) {
        val item : PermissionItem = list[position]
        return holder.bind(item, position)
    }

    fun isPermissionSelected(position : Int) : Boolean {
        return list[position].isChecked
    }

    fun getItemName(position : Int) : String {
        return list[position].name
    }

    fun getCheckedItems() : List<PermissionItem> {
        return list.filter { item -> item.isChecked }
    }


    class PermissionListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private var tv_name : TextView? = null
        private var cb_checkBox : CheckBox? = null

        init {
            tv_name = itemView.findViewById(R.id.tv_permission_name)
            cb_checkBox = itemView.findViewById(R.id.cb_item_permission)
        }

        fun bind(item : PermissionItem, position : Int) {
            tv_name?.text = item.name
            cb_checkBox?.isChecked = item.isChecked
            cb_checkBox?.setOnClickListener {
                if (cb_checkBox !!.isChecked) {
                    cb_checkBox !!.isChecked = true
                    item.isChecked = true
                    Log.d("MAIN: ", "ischecked!")
                } else {
                    cb_checkBox !!.isChecked = false
                    item.isChecked = false
                    Log.d("MAIN: ", "isunchecked!")
                }
            }
        }
    }

}
