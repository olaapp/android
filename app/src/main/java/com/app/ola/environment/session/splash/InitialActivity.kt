package com.app.ola.environment.session.splash
//import com.app.ola.environment.main.aes.HomeActivity
//import com.app.ola.environment.session.login.LoginViewModel
import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.ola.R
import com.app.ola.core.Core
import com.app.ola.core.util.*
import com.app.ola.environment.main.HomeOla
import com.app.ola.environment.main.IntroActivity
import com.app.ola.environment.main.aes.AESViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule


class InitialActivity : AppCompatActivity(),DefaultFlow {
    var isLoadingDeptos = false
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
//    private val viewModel by lazy {
//        initViewModel<LoginViewModel>(viewModelFactory)
//    }
    private val viewModelCatalogs by lazy {
        initViewModel<AESViewModel>(viewModelFactory)
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        configureStatusBarColor(R.color.colorPrimary)

        Timer("SettingUp", false).schedule(2000) {
            auth = Firebase.auth
            val currentUser = auth.currentUser
            Log.e("ESTADO ACTUAL: ", currentUser.toString())
                proceed()
            FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
                log("token: ${it.token}")
            }
        }
        initFlow()
        //val locale: String = this.resources.configuration.locale.country

        val tm: TelephonyManager  = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager;
        var countryCode = tm.simCountryIso;

        Log.e("XXXXXXXXXXXX",countryCode)
        if(countryCode=="sv"){
            countryCode="+503"
        }
        if(countryCode=="gt"){
            countryCode="+502"
        }
        setPreference("country_code",countryCode)
    }
    private fun proceed(){
            var userStatus = FirebaseAuth.getInstance().getCurrentUser()
            if(userStatus==null){
                launchActivity<IntroActivity>(true)
            }
        else{
                launchActivity<HomeOla>(true)
            }

    }
    override fun initFlow() {
        Core.DI.inject(this)
    }





}