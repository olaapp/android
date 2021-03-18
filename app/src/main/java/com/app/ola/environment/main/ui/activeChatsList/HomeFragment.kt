package com.app.ola.environment.main.ui.activeChatsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.app.ola.R
import com.app.ola.core.util.*
import com.app.ola.environment.main.ChatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.sendbird.android.GroupChannel
import com.sendbird.android.SendBird
import com.sendbird.android.SendBirdException
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import kotlinx.android.synthetic.main.row_doctors.view.*

//import com.app.ola.environment.main.R

class HomeFragment : Fragment() {

  private lateinit var homeViewModel: HomeViewModel
  val APP_ID = "511DBF3B-D804-4911-813F-236B9F6E6504" // Sample App
  // MutableList<GroupChannel>
  private lateinit var adapterChannles: DynamicAdapter<GroupChannel>
  val db = FirebaseFirestore.getInstance()

  override fun onCreateView(
          inflater: LayoutInflater,
          container: ViewGroup?,
          savedInstanceState: Bundle?
  ): View? {
    homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    val root = inflater.inflate(R.layout.fragment_home, container, false)
    displayInfo(root)
//    SendBird.init(APP_ID, context)
 //   getChannels()
//    val textView: TextView = root.findViewById(R.id.text_home)
//    homeViewModel.text.observe(viewLifecycleOwner, Observer {
//      textView.text = it
//    })
//    val bsb = BottomSheetBehavior.from(bottomSheet)
//            bsb.state = BottomSheetBehavior.STATE_EXPANDED

//    val db = FirebaseFirestore.getInstance()
//    val notesCollectionRef = db.collection("notes")
//    val jsaDocumentRef = db.collection("notes").document("jsa")
//    val androidTutRef = db
//            .collection("notes").document("jsa")
//            .collection("tutorials").document("androidTutRef")

//
//    val clq: OpenChannelListQuery = OpenChannel.createOpenChannelListQuery()
//    clq.next(object : OpenChannelListQueryResultHandler() {
//      fun onResult(list: List<OpenChannel?>, e: SendBirdException?) {
//        Log.i(TAG, "query results, " + list.size().toString() + " open channels")
//      }
//    })

    var myID = context?.getStringPreference("collection_id")
    connectToSendBird(
            myID.toString(),
            "Amig@",
            root
    )

    val crashButton = Button(context)
    crashButton.setText("Crash!")
    crashButton.setOnClickListener(View.OnClickListener {
      throw RuntimeException("Test Crash") // Force a crash
    })


    return root
  }

  private fun connectToSendBird(userID: String, nickname: String, v: View) {
    SendBird.init("511DBF3B-D804-4911-813F-236B9F6E6504", context)
    SendBird.connect(userID) { user, e ->



      if (e != null) {

      } else {
        SendBird.updateCurrentUserInfo(nickname, null) { e ->
          if (e != null) {

          }
          getChannels(v)
        }
      }


    }
  }

  fun getChannels(v: View){

    val channelList = GroupChannel.createMyGroupChannelListQuery()
    channelList.limit = 100
    channelList.next { list, e ->
      if (e != null) {
        Log.e("TAG", e.message)
      }

      var entries = ArrayList<GroupChannel>()
      //entries.add(list)
      list.forEach { row->
        entries.add(row)
        Log.e("ROW:", row.toString())
      }

      adapterChannles = DynamicAdapter(
              R.layout.row_doctors,
              entries,
              fun(_, v, ex, p) {
                v.txDoctorName.text = ex.name
                v.txDoctorSpeciality.text = ex.data.toString()

                v.btnExistentChat.setOnClickListener {
                  Log.e("CHAT", ex.url.toString())

                  context?.setPreference("chat_url", ex.url.toString())
                  context?.setPreference("doctor_name", ex.name)
                  activity?.launchActivity<ChatActivity> { }
                }

              })
      val rvd: RecyclerView = v.findViewById(R.id.btnChats)
      rvd.configureRecycler(adapterChannles, true)
      adapterChannles.notifyDataSetChanged()



      //adapter.addChannels(list)
    }



  }

  fun displayInfo(v: View) {
    var myID = context?.getStringPreference("collection_id")
    var user_type = requireActivity().getStringPreference("user_type")
    Log.e("user_type",user_type)
    if(user_type=="doctor"){
      var docRef = db.collection("doctors").document(myID.toString())
      docRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
        if (task.isSuccessful) {
          val document = task.result
          if (document != null) {


            var vxName = task.result!!.data!!.get("alias").toString()

            if (vxName.length == 0) {
              vxName = "Amig@"
            }

            v.txNAMEX.text = vxName


          } else {
            //Log.d(TAG, "No such document")
          }
        } else {
          //Log.d(TAG, "get failed with ", task.exception)
        }
      })
    }
    else{
      var docRef = db.collection("users").document(myID.toString())
      docRef.get().addOnCompleteListener(OnCompleteListener<DocumentSnapshot> { task ->
        if (task.isSuccessful) {
          val document = task.result
          if (document != null) {


            var vxName = task.result!!.data!!.get("name").toString()

            if (vxName.length == 0) {
              vxName = "Amig@"
            }

            v.txNAMEX.text = vxName


          } else {
            //Log.d(TAG, "No such document")
          }
        } else {
          //Log.d(TAG, "get failed with ", task.exception)
        }
      })
    }






  }


}