package com.app.ola.environment.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.ola.R
import com.app.ola.core.util.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.sendbird.android.SendBird
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_validate_phone.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*
import java.util.concurrent.TimeUnit



class ValidatePhoneActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    var userType = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validate_phone)
        auth = FirebaseAuth.getInstance()
        prgLoading2.fadeOutAnimate()
        btnNext.setOnClickListener {
//            launchActivity<HomeOla> {  }

            validate(txVerificationPhone.text.toString())
        }
        btnResend.setOnClickListener {
            prgLoading2.fadeInAnimate()

            var px = getStringPreference("phone")
            var phoneCode = getStringPreference("country_code")
            if (phoneCode.length>0){

            }
            else{
                phoneCode = "+502"
            }

            resendVerificationCode(phoneCode+px,getStringPreference("token_verification"))
        }
    }
    fun validate(txtCode: String){
        if(txtCode.length>0){
            prgLoading2.fadeInAnimate()
            var credential = PhoneAuthProvider.getCredential(getStringPreference("token_verification"), txtCode)
            signInWithPhoneAuthCredential(credential)
        }
        else{
            toastyError("Ingrese el código recibido por SMS")
        }

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        toastySuccess("Teléfono validado correctamente")
                        val user = task.result?.user
                        lookupNumberInFirestore()
                    } else {

                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
                            toastyError("El código ingresado no es válido, intente nuevamente")
                            prgLoading2.fadeOutAnimate()
                        }
                    }
                }
    }
    fun createUserFirestore(){

        var px = getStringPreference("phone")

var TAG ="FIRESTORE"
        val docData = hashMapOf(
                "active" to true,
                "age" to "",
                "chats" to "",
                "city" to "Guatemala",
                "country" to "Guatemala",
                "email" to "",
                "gender" to "Masculino",
                "lastname" to "",
                "name" to "",
                "online" to true,
                "phone_number" to px,
                "photo" to ""
        )
        db.collection("users")
                .add(docData)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                    setPreference("collection_id", documentReference.id.toString())
                    toastySuccess("Usuario registrado correctamente")
                    lookupNumberInFirestore()
                    //launchActivity<ConfirmationActivity> (true) {  }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
    }
fun lookupNumberInFirestore(){
    var px = getStringPreference("phone")
    var existUser=false


    Log.e("Number", px)

    db.collection("doctors")
            .whereEqualTo("phone_number", px)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.e("EXISTE", "BUSCANDO DOCTOR")
                    Log.d("EXISTE", "${document.id} => ${document.data}")
                    existUser=true
                    if(document.id.length>0){
                        setPreference("collection_id", document.id.toString())
                        val docRef = db.collection("doctors").document(document.id.toString())
                        //connectToSendBird(document.id.toString(), docRef.alias)
                        var vxName=""
                        docRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
                            if (task.isSuccessful) {
                                val document = task.result
                                if (document != null) {

                                    vxName = task.result!!.data!!.get("alias").toString()
                                    connectToSendBird(document.id.toString(), vxName)

                                } else {
                                    //Log.d(TAG, "No such document")
                                }
                            } else {
                                //Log.d(TAG, "get failed with ", task.exception)
                            }
                        })

                        toastySuccess("Bienvenido Doctor "+vxName)
                        setPreference("user_type","doctor")
                    }
                }
            }
            .addOnFailureListener { exception ->

                Log.e("NO", "Error getting documents: ", exception)
                toastyError("Error: " + exception.toString())
                createUserFirestore()
            }
            .addOnCompleteListener{
                Log.e("QUERY", "Query complete")
            }
        if(!existUser){
            db.collection("users")
                    .whereEqualTo("phone_number", px)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            Log.e("EXISTE", "EXISTE")
                            Log.d("EXISTE", "${document.id} => ${document.data}")
                            existUser=true
                            if(document.id.length>0){
                                setPreference("collection_id", document.id.toString())
                                connectToSendBird(document.id.toString(), "Amig@")
                                setPreference("user_type","user")
                            }
                        }
                    }
                    .addOnFailureListener { exception ->

                        Log.e("NO", "Error getting documents: ", exception)
                        toastyError("Error: " + exception.toString())
                        createUserFirestore()
                    }
                    .addOnCompleteListener{
                        Log.e("QUERY", "Query complete")
                        if(!existUser){
                            createUserFirestore()
                        }
                    }
        }



    }

    private fun resendVerificationCode(phoneNumber: String,
                                       token: String) {
        toastySuccess("Código de verificación re-enviado")
        prgLoading2.fadeOutAnimate()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,  // Phone number to verify
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                this,  // Activity (for callback binding)
                (object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    override fun onCodeSent(
                            verificationId: String,
                            forceResendingToken: PhoneAuthProvider.ForceResendingToken
                    ) {
                        // Save the verification id somewhere
                        // ...

                        // The corresponding whitelisted code above should be used to complete sign-in.
                        //this@RegisterLoginActivity.enableUserManuallyInputCode()
                        prgLoading2.fadeOutAnimate()
                        toastySuccess("Código re-enviado")
                        setPreference("token_verification",verificationId)
                        launchActivity<ValidatePhoneActivity>(true) {  }
                    }

                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                        // Sign in with the credential
                        // ...
                        //launchActivity<HomeOla>(true) {  }
                        toastySuccess("Número telefónico verificado")
                    }

                    override fun onVerificationFailed(p0: FirebaseException) {
                        // ...
                        toastyError("No pudimos verificar tu número: "+p0.localizedMessage.toString())
                        Log.e("Error: ",p0.localizedMessage.toString())
                    }
                })
                ,  // OnVerificationStateChangedCallbacks
                 null) // ForceResendingToken from callbacks
    }

    private fun connectToSendBird(userID: String, nickname: String) {
        SendBird.init("511DBF3B-D804-4911-813F-236B9F6E6504", this)
        SendBird.connect(userID) { user, e ->
            if (e != null) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            } else {
                SendBird.updateCurrentUserInfo(nickname, null) { e ->
                    if (e != null) {
                        Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                    }
                    launchActivity<ConfirmationActivity>(true) {  }
                    finish()
                }
            }
        }
    }

}