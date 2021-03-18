package com.app.ola.environment.main

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.app.ola.R
import com.app.ola.core.util.*
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.content_register_login.*
import java.util.concurrent.TimeUnit

class RegisterLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_login)
        prgLoading.fadeOutAnimate()
        btnNext.setOnClickListener {
//            launchActivity<ValidatePhoneActivity> {  }
            prgLoading.fadeInAnimate()
            createAccount(txPhoneNumber.text.toString())
        }

    }

    fun createAccount(phoneNumber:String){
        //Log.e("PHONE: ","+503"+phoneNumber)
        toastyInfo("Espere un momento")
        setPreference("phone",phoneNumber)


//        val phoneNum = "+16505554567"
//        val testVerificationCode = "123456"
        var phoneCode = getStringPreference("country_code")
        if (phoneCode.length>0){

        }
        else{
            phoneCode = "+502"
        }
        val options = PhoneAuthOptions.newBuilder(Firebase.auth)
                .setPhoneNumber(phoneCode+phoneNumber)
                .setTimeout(30L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    override fun onCodeSent(
                            verificationId: String,
                            forceResendingToken: PhoneAuthProvider.ForceResendingToken
                    ) {
                        // Save the verification id somewhere
                        // ...

                        // The corresponding whitelisted code above should be used to complete sign-in.
                        //this@RegisterLoginActivity.enableUserManuallyInputCode()
                        setPreference("token_verification",verificationId)
                        launchActivity<ValidatePhoneActivity>(true) {  }
                    }

                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                        // Sign in with the credential
                        // ...
                        launchActivity<HomeOla>(true) {  }
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        // ...
                        toastyError("No pudimos verificar tu número: "+p0.localizedMessage.toString())
                        Log.e("Error: ",p0.localizedMessage.toString())
                    }
                })
                .build()
        PhoneAuthProvider.verifyPhoneNumber(options)



    }
}