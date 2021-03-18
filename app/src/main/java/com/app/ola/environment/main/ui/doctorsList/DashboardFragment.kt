package com.app.ola.environment.main.ui.doctorsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.app.ola.R
import com.app.ola.core.util.*
import com.app.ola.environment.main.ChatActivity
import com.app.ola.environment.main.ui.Doctors
import com.app.ola.environment.main.ui.Users
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.sendbird.android.GroupChannel
import com.sendbird.android.GroupChannelParams
import com.sendbird.android.SendBird
import kotlinx.android.synthetic.main.row_doctors.view.*

//import com.app.ola.environment.main.R

class DashboardFragment : Fragment() {

  private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var adapterDoctors: DynamicAdapter<Doctors>
    var doctorName = ""
    var medicalSpeciality = ""
    var doctorPicture = ""
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

    readDataFromFirestore(root)



    return root
  }


  private fun readDataFromFirestore(v:View){
    val db = FirebaseFirestore.getInstance()
    var doctorsList = ArrayList<Doctors>()
      var usersList = ArrayList<Users>()
    Log.e("Starting","Starting select")

    db.collection("doctors").get()
            .addOnSuccessListener {
              documents ->
              for(document in documents){
//                Log.e("DOKTOR","${document.id} => ${document.data}")
                doctorsList.add(
                        Doctors(
                                document.id.toString(),
                                document.data.get("active").toString(),
                                document.data.get("alias").toString(),
                                document.data.get("lastname").toString(),
                                document.data.get("medical_speciality").toString(),
                                document.data.get("name").toString(),
                                document.data.get("online").toString(),
                                document.data.get("photo").toString(),
                                usersList

                        )
                )
              }
                fillRV(v,doctorsList)
            }
            .addOnFailureListener { exception ->
              Log.e("DOKTOR", "Error getting documents: ", exception)
            }
  }

    fun fillRV(v:View,entries:ArrayList<Doctors>){
        var myID = context?.getStringPreference("collection_id")

        adapterDoctors = DynamicAdapter(
                R.layout.row_doctors,
                entries,
                fun(_, v, ex, p) {
                    v.txDoctorName.text = ex.alias
                    v.txDoctorSpeciality.text = ex.medical_speciality
                    //imgChat
                    Glide.with(this).load(ex.photo).into(v.imgChat)
                    var sselectedUsers: ArrayList<String>
                    var doctorsNames: ArrayList<String>

                    v.setOnClickListener {
                        //create chanenl
                        doctorName=ex.alias.toString()
                        medicalSpeciality=ex.medical_speciality.toString()
                        doctorPicture  = ex.photo.toString()
                        sselectedUsers = ArrayList()

                        sselectedUsers.add(ex.id.toString())
                        sselectedUsers.add(myID.toString())

                        connectToSendBird(myID.toString(),"Amig@",sselectedUsers)

//                        createChannel(sselectedUsers)
                    }

                })
        val rvd: RecyclerView = v.findViewById(R.id.rvDoctors)
        rvd.configureRecycler(adapterDoctors, true)
        adapterDoctors.notifyDataSetChanged()
    }

    private fun connectToSendBird(userID: String, nickname: String,users:MutableList<String>) {
        SendBird.init("511DBF3B-D804-4911-813F-236B9F6E6504", context)
        SendBird.connect(userID) { user, e ->
            if (e != null) {

            } else {
                SendBird.updateCurrentUserInfo(nickname, null) { e ->
                    if (e != null) {

                    }
                    createChannel(users)
                }
            }
        }
    }

    fun createChannel(users:MutableList<String>){
        val params = GroupChannelParams()

        val operatorId = ArrayList<String>()
        operatorId.add(SendBird.getCurrentUser().userId)

        params.addUserIds(users)
        params.setOperatorUserIds(operatorId)

        params.setName(doctorName)
        //params.setData(medicalSpeciality)
        //params.setCoverImage(doctorPicture)

        GroupChannel.createChannel(params) { groupChannel, e ->
            if (e != null) {
                Log.e("TAG", e.message)
            } else {
                    //context?.toastySuccess("Canal: "+groupChannel.url.toString())
                    context?.setPreference("chat_url",groupChannel.url.toString())
                    context?.setPreference("doctor_name",doctorName)
                    activity?.launchActivity<ChatActivity> {

                    }
//                val intent = Intent(this, ChannelActivity::class.java)
//                intent.putExtra(EXTRA_CHANNEL_URL, groupChannel.url)
//                startActivity(intent)
            }
        }
    }

}