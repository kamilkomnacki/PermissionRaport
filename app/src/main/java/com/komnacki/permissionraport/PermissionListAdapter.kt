package com.komnacki.permissionraport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PermissionListAdapter(private val list : ArrayList<PermissionItem>) :
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
        return holder.bind(item)
    }


    class PermissionListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private var tv_name : TextView? = null
        private var cb_checkBox : CheckBox? = null

        init {
            tv_name = itemView.findViewById(R.id.tv_permission_name)
            cb_checkBox = itemView.findViewById(R.id.cb_item_permission)
//            view.setOnClickListener {
//                cb_checkBox?.isChecked = (cb_checkBox?.isChecked) !!
//            }
        }

        fun bind(item : PermissionItem) {
            tv_name?.text = item.name
            cb_checkBox?.isChecked = item.isChecked
        }
    }

}
