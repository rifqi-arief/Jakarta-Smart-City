package com.nutech.jakartasmartcity.utils

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.nutech.jakartasmartcity.R
import java.io.*
import java.net.NetworkInterface
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class Common() {
    companion object {
        fun movePage(fragmentManager: FragmentManager, fragment: Fragment) {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_main, fragment)
                addToBackStack("fragment")
                commit()
            }
        }

        fun replacePage(fragmentManager: FragmentManager, fragment: Fragment) {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frame_main, fragment)
                commit()
            }
        }
    }
}