package com.app.ola.environment.main.ui.notifications

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.ola.R
import com.app.ola.core.util.*
import com.app.ola.environment.session.splash.InitialActivity
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.nguyenhoanglam.imagepicker.model.Image
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.dlg_input.*
import kotlinx.android.synthetic.main.dlg_input.btnNext_theft_a
import kotlinx.android.synthetic.main.dlg_input.titlex_2a
import kotlinx.android.synthetic.main.dlg_input_gender.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import kotlinx.android.synthetic.main.row_doctors.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


//import com.app.ola.environment.main.R

class NotificationsFragment : Fragment() {

  private lateinit var notificationsViewModel: NotificationsViewModel
  var storageRef = FirebaseStorage.getInstance().reference
  val db = FirebaseFirestore.getInstance()
  val TAG = "NOTF"
  lateinit var root:View

  lateinit var dlgTextInput: Dialog

  lateinit var dlgTextInputSP: Dialog

  lateinit var dlgTextInputSPGender: Dialog

  val myCalendar: Calendar = Calendar.getInstance()

  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View? {
    notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)
    root = inflater.inflate(R.layout.fragment_notifications, container, false)

    if(requireActivity().getStringPreference("user_type")=="doctor"){
      displayInfoDoctor(root)
      root.lyUsers.fadeOutAnimate()
      root.lyDoctors.fadeInAnimate()
      root.btnSaveDoctor.fadeInAnimate()
    }
    else{
      displayInfo(root)
      root.lyUsers.fadeInAnimate()
      root.lyDoctors.fadeOutAnimate()
      root.btnSaveDoctor.fadeOutAnimate()
    }

    root.btnChangePicture.setOnClickListener {
      loadPicker()
    }

    dlgTextInput = Dialog(this.requireContext())
    dlgTextInput.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dlgTextInput.setCancelable(true)
    dlgTextInput.setContentView(R.layout.dlg_input)
    dlgTextInput.btnNext_theft_a.setOnClickListener {
      dlgTextInput.dismiss()
      //finish()
    }


    dlgTextInputSP = Dialog(this.requireContext())
    dlgTextInputSP.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dlgTextInputSP.setCancelable(true)
    dlgTextInputSP.setContentView(R.layout.dlg_input)
    dlgTextInputSP.btnNext_theft_a.setOnClickListener {
      dlgTextInputSP.dismiss()
      //finish()
    }


    dlgTextInputSPGender = Dialog(this.requireContext())
    dlgTextInputSPGender.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dlgTextInputSPGender.setCancelable(true)
    dlgTextInputSPGender.setContentView(R.layout.dlg_input_gender)
    dlgTextInputSPGender.btnNext_theft_a.setOnClickListener {
      dlgTextInputSPGender.dismiss()
      //finish()
    }

    val nebulae = arrayOf<String>("Seleccione","Femenino", "Masculino")
    var adapter= ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,nebulae)
    dlgTextInputSPGender.txtValuex.adapter=adapter


    root.btnClose.setOnClickListener {
      AlertDialog.Builder(this.activity)
              .setTitle("Cerrar sesión")
              .setMessage("¿Está seguro que desea cerrar sesión?")
              .setPositiveButton(android.R.string.yes) { _, _ -> yesClicked() }
              .setNegativeButton(android.R.string.no) { _, _ -> noClicked() }
              .show()
    }

    root.brthdayx.setOnClickListener {
      dlgTextInputSP.titlex_2a.text="¿Cuántos años tienes?"
      val date = OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        myCalendar[Calendar.YEAR] = year
        myCalendar[Calendar.MONTH] = monthOfYear
        myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
        updateLabel()
      }

      dlgTextInputSP.txtValue.isClickable=true
      dlgTextInputSP.txtValue.keyListener=null

      dlgTextInputSP.txtValue.setOnClickListener {
        DatePickerDialog(requireContext(), date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]).show()

        val myFormat = "dd/MM/yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dlgTextInputSP.txtValue.setText(sdf.format(myCalendar.time).toString())

      }
      dlgTextInputSP.btnNext_theft_a.setOnClickListener {
        var newValue= dlgTextInputSP.txtValue.text.toString()
        updateField("age", newValue, root)
        dlgTextInputSP.dismiss()
        dlgTextInputSP.txtValue.setText("")
      }
      dlgTextInputSP.show()
      val window: Window = dlgTextInputSP.window!!
      window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    root.txAddress.setOnClickListener {
      dlgTextInput.titlex_2a.text="¿Cuál es tu dirección?"
      dlgTextInput.btnNext_theft_a.setOnClickListener {
        var newValue= dlgTextInput.txtValue.text.toString()
        updateField("city", newValue, root)
        dlgTextInput.dismiss()
        dlgTextInput.txtValue.setText("")
      }
      dlgTextInput.show()
      val window: Window = dlgTextInput.window!!
      window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

    }

    root.txName.setOnClickListener {
      dlgTextInput.titlex_2a.text="¿Cuál es tu nombre?"
      dlgTextInput.btnNext_theft_a.setOnClickListener {
        var newValue= dlgTextInput.txtValue.text.toString()
        updateField("name", newValue, root)
        dlgTextInput.dismiss()
        dlgTextInput.txtValue.setText("")
      }
      dlgTextInput.show()
      val window: Window = dlgTextInput.window!!
      window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

    }

    root.genderx.setOnClickListener {
      dlgTextInputSPGender.titlex_2a.text="¿Cuál es tu género?"
      dlgTextInputSPGender.btnNext_theft_a.setOnClickListener {
        var newValue= dlgTextInputSPGender.txtValuex.selectedItem.toString()
        updateField("gender", newValue, root)
        dlgTextInputSPGender.dismiss()
//        dlgTextInputSPGender.txtValue.setText("")
      }
      dlgTextInputSPGender.show()
      val window: Window = dlgTextInputSPGender.window!!
      window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    }

    root.documentx.setOnClickListener {
      dlgTextInput.titlex_2a.text="Coloca tu identificación"
      dlgTextInput.btnNext_theft_a.setOnClickListener {
        var newValue= dlgTextInput.txtValue.text.toString()
        updateField("document", newValue, root)
        dlgTextInput.dismiss()
        dlgTextInput.txtValue.setText("")
      }
      dlgTextInput.show()
      val window: Window = dlgTextInput.window!!
      window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

    }

    root.btnSaveDoctor.setOnClickListener {
      updateDoctors(root)

    }

    //calendar

    //calendar

    return root
  }
  fun updateLabel(){

    val myFormat = "MM/dd/yy" //In which you need put here

    val sdf = SimpleDateFormat(myFormat, Locale.US)
    txAge.text = sdf.format(myCalendar.time)

  }

  fun yesClicked(){
    FirebaseAuth.getInstance().signOut()
    activity?.launchActivity<InitialActivity> {  }
  }
  fun noClicked(){

  }
  fun updateDoctors(v: View){
    var myID = context?.getStringPreference("collection_id")
    var special = v.etEspecialidad.text.toString()
    db.collection("doctors").document(myID.toString()).update("medical_speciality", special)
    displayInfoDoctor(v)
    requireActivity().toastySuccess("Actualizado correctamente")
  }
  fun updateField(fieldName: String, fieldValue: String, v: View){
    var myID = context?.getStringPreference("collection_id")


    if(requireActivity().getStringPreference("user_type")=="doctor"){
      db.collection("doctors").document(myID.toString()).update(fieldName, fieldValue)
      displayInfoDoctor(v)
    }
    else{
      db.collection("users").document(myID.toString()).update(fieldName, fieldValue)
      displayInfo(v)
    }

  }
  fun displayInfo(v: View) {
    var myID = context?.getStringPreference("collection_id")
    val docRef = db.collection("users").document(myID.toString())

    docRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
      if (task.isSuccessful) {
        val document = task.result
        if (document != null) {
          Log.d(TAG, "DocumentSnapshot data: " + task.result!!.data)

          var vxName = task.result!!.data!!.get("name").toString()
          var vxAdress = task.result!!.data!!.get("city").toString()
          var vxAge = task.result!!.data!!.get("age").toString()
          var vxGender = task.result!!.data!!.get("gender").toString()
          var vxDocument = task.result!!.data!!.get("document").toString()

          if (vxName.length == 0) {
            vxName = "Toca para editar"
          }
          if (vxAdress.length == 0) {
            vxAdress = "Toca para editar"
          }
          if (vxAge.length == 0) {
            vxAge = "Toca para editar"
          }
          if (vxGender.length == 0) {
            vxGender = "Toca para editar"
          }
          if (vxDocument.length == 0) {
            vxDocument = "Toca para editar"
          }

          v.txName.text = vxName
          v.txAddress.text = vxAdress
          v.txAge.text = vxAge
          v.txGender.text = vxGender
          v.txDocument.text = vxDocument

          var picurl = task.result!!.data!!.get("photo").toString()
          if (picurl.length == 0) {
            picurl = "https://firebasestorage.googleapis.com/v0/b/aes-app-755d2.appspot.com/o/bad-profile-pic-2-768x768.jpeg?alt=media&token=c23dd144-af68-401b-b573-ae642b3c7bc0"
          }
          //circleImageView
          Glide.with(this).load(picurl).into(v.circleImageView)

        } else {
          //Log.d(TAG, "No such document")
        }
      } else {
        //Log.d(TAG, "get failed with ", task.exception)
      }
    })
  }

  fun displayInfoDoctor(v: View) {
    var myID = context?.getStringPreference("collection_id")
    val docRef = db.collection("doctors").document(myID.toString())

    docRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
      if (task.isSuccessful) {
        val document = task.result
        if (document != null) {
          Log.d(TAG, "DocumentSnapshot data: " + task.result!!.data)

          var vxName = task.result!!.data!!.get("name").toString()
          var vxSpeciality = task.result!!.data!!.get("medical_speciality").toString()
          v.etEspecialidad.setText(vxSpeciality)
          //var vxAdress = task.result!!.data!!.get("city").toString()
          //var vxAge = task.result!!.data!!.get("age").toString()
          //var vxGender = task.result!!.data!!.get("gender").toString()
          //var vxDocument = task.result!!.data!!.get("document").toString()

          if (vxName.length == 0) {
            vxName = "Toca para editar"
          }
//          if (vxAdress.length == 0) {
//            vxAdress = "Toca para editar"
//          }
//          if (vxAge.length == 0) {
//            vxAge = "Toca para editar"
//          }
//          if (vxGender.length == 0) {
//            vxGender = "Toca para editar"
//          }
//          if (vxDocument.length == 0) {
//            vxDocument = "Toca para editar"
//          }

          v.txName.text = vxName
//          v.txAddress.text = vxAdress
//          v.txAge.text = vxAge
//          v.txGender.text = vxGender
//          v.txDocument.text = vxDocument

          var picurl = task.result!!.data!!.get("photo").toString()
          if (picurl.length == 0) {
            picurl = "https://firebasestorage.googleapis.com/v0/b/aes-app-755d2.appspot.com/o/bad-profile-pic-2-768x768.jpeg?alt=media&token=c23dd144-af68-401b-b573-ae642b3c7bc0"
          }
          //circleImageView
          Glide.with(this).load(picurl).into(v.circleImageView)

        } else {
          //Log.d(TAG, "No such document")
        }
      } else {
        //Log.d(TAG, "get failed with ", task.exception)
      }
    })
  }

  fun loadPicker() {

    ImagePicker.with(this) //  Initialize ImagePicker with activity or fragment context
            .setToolbarColor("#0075B1") //  Toolbar color
            .setStatusBarColor("#0075B1") //  StatusBar color (works with SDK >= 21  )
            .setToolbarTextColor("#FFFFFF") //  Toolbar text color (Title and Done button)
            .setToolbarIconColor("#FFFFFF") //  Toolbar icon color (Back and Camera button)
            .setProgressBarColor("#0075B1") //  ProgressBar color
            .setBackgroundColor("#FFFFFF") //  Background color
            .setCameraOnly(false) //  Camera mode
            .setMultipleMode(true) //  Select multiple images or single image
            .setFolderMode(true) //  Folder mode
            .setShowCamera(true) //  Show camera button
            .setImageTitle("Seleccione") //  Image title (works with FolderMode = false)
            .setDoneTitle("Terminar") //  Done button title
            .setLimitMessage("Máximo de imágenes seleccionadas") // Selection limit message
            .setMaxSize(5) //  Max images can be selected
            .setDirectoryName("AESFOTOS")
            .setAlwaysShowDoneButton(true) //  Set always show done button in multiple mode
            .start()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    if (ImagePicker.shouldHandleResult(requestCode, resultCode, data, 100)) {
      val images: ArrayList<Image> = ImagePicker.getImages(data)
      // Do stuff with image's path or id. For example:
      for (image in images) {
        uploadFile(image.path)
      }
    }
    super.onActivityResult(requestCode, resultCode, data)   // This line is REQUIRED in fragment mode
  }


  fun uploadFile(s: String) {
    var myID = context?.getStringPreference("collection_id")
    var file = Uri.fromFile(File(s))
    GlobalScope.launch {


      val ref = storageRef.child("users_profile_android/${file.lastPathSegment}")
      var uploadTask = ref.putFile(file)
      val urlTask = uploadTask.continueWithTask { task ->
        if (!task.isSuccessful) {
          task.exception?.let {
            throw it
          }
        }
        ref.downloadUrl
      }.addOnCompleteListener { task ->
        if (task.isSuccessful) {
          val downloadUri = task.result
          db.collection("users").document(myID.toString()).update("photo", downloadUri.toString())
          displayInfo(root)
        } else {

        }
      }
    }

  }

}