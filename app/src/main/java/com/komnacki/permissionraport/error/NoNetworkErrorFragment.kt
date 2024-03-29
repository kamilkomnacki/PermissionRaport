package com.komnacki.permissionraport.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.komnacki.permissionraport.R

class NoNetworkErrorFragment : Fragment() {
    companion object {
        fun newInstance() : NoNetworkErrorFragment {
            return NoNetworkErrorFragment()
        }
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_no_network_error, container, false)
    }
}