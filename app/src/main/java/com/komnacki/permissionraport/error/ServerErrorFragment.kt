package com.komnacki.permissionraport.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.komnacki.permissionraport.R

class ServerErrorFragment : Fragment() {
    companion object {
        fun newInstance() : ServerErrorFragment {
            return ServerErrorFragment()
        }
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_server_error, container, false)
    }
}