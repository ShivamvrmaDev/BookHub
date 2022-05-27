package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task


class Upload : AppCompatActivity() {
    var  RESOLVE_HINT =12
    var request=2
lateinit var edit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


edit=findViewById(R.id.edit)

edit.setOnClickListener {
    requestHint()
}

    // Get an instance of SmsRetrieverClient, used to start listening for a matching
// SMS message.
    // Get an instance of SmsRetrieverClient, used to start listening for a matching
// SMS message.
    val client = SmsRetriever.getClient(this /* context */)

// Starts SmsRetriever, which waits for ONE matching SMS message until timeout
// (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
// action SmsRetriever#SMS_RETRIEVED_ACTION.

// Starts SmsRetriever, which waits for ONE matching SMS message until timeout
// (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
// action SmsRetriever#SMS_RETRIEVED_ACTION.
    val task: Task<Void> = client.startSmsRetriever()

// Listen for success/failure of the start Task. If in a background thread, this
// can be made blocking using Tasks.await(task, [timeout]);

// Listen for success/failure of the start Task. If in a background thread, this
// can be made blocking using Tasks.await(task, [timeout]);
    task.addOnSuccessListener(OnSuccessListener<Void?> {
        var intent=Intent()
        intent.setAction(SmsRetriever.SMS_RETRIEVED_ACTION)
        var broad=Broadcaster()
        broad.onReceive(this,intent)




    })

    task.addOnFailureListener(OnFailureListener {
        // Failed to start retriever, inspect Exception for more details
        // ...
    })



    }
    private fun requestHint() {
        val hintRequest = HintRequest.Builder()
            .setPhoneNumberIdentifierSupported(true)
            .build()

            val intent= Credentials.getClient(this).getHintPickerIntent(
               hintRequest
            )

            startIntentSenderForResult(
                intent.getIntentSender(),
                RESOLVE_HINT, null, 0, 0, 0
            )

    }

    // Obtain the phone number from the result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESOLVE_HINT) {
            if (resultCode == RESULT_OK) {
                val credential: Credential? = data?.getParcelableExtra(Credential.EXTRA_KEY)
                // credential.getId();  <-- will need to process phone number string
            }
        }
    }

}