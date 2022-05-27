package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.Status


class Broadcaster : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.getAction()) {
            val extras: Bundle? = intent.getExtras()
            val status: Status = extras?.get(SmsRetriever.EXTRA_STATUS) as Status
            when (status.statusCode) {
                CommonStatusCodes.SUCCESS -> {           // Get SMS message contents
                    extras.get(SmsRetriever.EXTRA_SMS_MESSAGE) as String?
                }
                CommonStatusCodes.TIMEOUT -> {
                }
            }
        }
    }
}
