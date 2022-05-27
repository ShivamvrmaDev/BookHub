package com.example.myapplication

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class Connection {

    fun ConnectionChecking(context : Context) : Boolean{

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var info : NetworkInfo? = connectivityManager.activeNetworkInfo

        if(info?.isConnected != null ){

            return info.isConnected}

        else
            return false
    }

}
