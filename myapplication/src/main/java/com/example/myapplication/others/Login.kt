package com.example.myapplication.others

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.activity.dashact
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.tasks.Task


const val RC_SIGN_IN =123
class Login :AppCompatActivity() {

    lateinit var login :SignInButton
var name :String?="wgdjg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         login = findViewById(R.id.sign_in_button)



        var signinoption=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        var signin=GoogleSignIn.getClient(this,signinoption)

        login.setOnClickListener {
            var intent = signin.signInIntent
            startActivityForResult(intent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(RC_SIGN_IN == requestCode){

            var task=GoogleSignIn.getSignedInAccountFromIntent(data)
            user(task)
            var intent=Intent(this@Login, dashact::class.java)
         intent.putExtra("name",name)
            startActivity(intent)
            finish()
        }

    }
    fun user(acc : Task<GoogleSignInAccount>){
  try {
    var account = acc.result

    name = account.displayName

}catch (e :Exception){

    makeText(this,"please try again",Toast.LENGTH_SHORT).show()


}
    }
}