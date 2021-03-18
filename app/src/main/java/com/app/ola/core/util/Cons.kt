package com.app.ola.core.util

/**
 * Created by Luis Mendoza (@luis-mendoza) on 10/9/2018.
 * https://www.linkedin.com/in/luis-mendoza
 */
object Cons {

    /**
     * Application name
     */
    const val APP_NAME = "aes"
    const val DEV_MODE = false
    const val APP_DB_NAME = "$APP_NAME-db"

    /** Development URL*/


    private const val BASE_URL_DEV = "https://app.movilaeswebdes.com/"
//    private const val BASE_URL_DEV = "https://app.movilaesweb.com/"


//    private const val BASE_URL_AES_CORE = "https://www.aeselsalvadormovil.com:8443/WcfmovilPR/AESMovil.svc/"
    private const val BASE_URL_AES_CORE = "https://www.aeselsalvadormovil.com:8443/WfcMovil_QA/AESMovil.svc/"

    private const val SECRET_AES = "13f45a391dbd50a72ec60de902ff24d5"
    /** Production URL*/
    //private const val BASE_URL_PROD = "https://aesmovildesarrollo.uc.r.appspot.com/"
    //private const val BASE_URL_PROD = "https://aes-mcac.com/"
    /**
     * Application Base URL
     */
    const val BASE_URL = BASE_URL_DEV
    const val URL_AES_CORE = BASE_URL_AES_CORE
    const val TOKEN_AES_CORE = SECRET_AES

//    const val DO_I_KNOW_GOD = "http://35.196.177.39/know-god-cms-api/cms/do-knowgod.php"
//    const val CAN_I_KNOW_GOD = "http://35.196.177.39/know-god-cms-api/cms/can-knowgod.php"

    const val USER_TOUR = "USER_TOUR"
    const val USER_PROVIDER = "USER_PROVIDER"
    const val USER_SESSION = "USER_SESSION"
    const val USER_ISBIOMMETRIC = "USER_ISBIOMETRIC"
    const val HAS_NICS = "HAS_NICS"
    const val USER_JWT = "USER_JWT"
    const val USER_UUID = "USER_UUID"
    const val USER_NAME = "USER_NAME"
    const val USER_EMAIL = "USER_EMAIL"
    const val USER_PICTURE = "USER_PICTURE"
    const val USER_ID = "ID"
    const val USER_NOTIFICATIONS = "USER_NOTIFICATIONS"
    const val USER_GENERAL_NOTIFICATIONS = "USER_GENERAL_NOTIFICATIONS"

    const val IDEAS_TYPE = "IDEAS_TYPE"
    const val QUESTION_TYPE = "QUESTION_TYPE"

    const val DATE = "DATE"

    const val CHANNEL_GENERAL = "general"


    const val USER_CARDID = "USER_CARDID"
    const val USER_CARDBRAND = "USER_CARDBRAND"
    const val USER_CARDFRIENDLY = "USER_CARDFRIENDLY"

    const val USER_ADDRESSID = "USER_ADDRESSID"
    const val USER_ADDRESSCONTENT = "USER_ADDRESSCONTENT"

    const val USER_PHONEID = "USER_PHONEID"
    const val USER_PHONENUMBER = "USER_PHONENUMBER"

    const val USER_BDAY = "USER_BDAY"
}