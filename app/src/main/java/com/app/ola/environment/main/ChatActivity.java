package com.app.ola.environment.main;


import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.inputmethodservice.Keyboard;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ola.R;
import com.app.ola.core.util.Cons;
import com.bumptech.glide.Glide;

import com.devlomi.record_view.OnRecordListener;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;


import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.facebook.internal.ImageRequest;

import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.sendbird.android.BaseChannel;
import com.sendbird.android.BaseMessage;
import com.sendbird.android.GroupChannel;
import com.sendbird.android.OpenChannel;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;
import com.sendbird.android.UserMessage;
///import com.thorny.photoeasy.PhotoEasy;


import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import coil.Coil;
import coil.ImageLoader;
import kotlin.jvm.functions.Function1;

import static com.app.ola.core.util.ExtensionsKt.getStringPreference;
import static com.app.ola.core.util.ExtensionsKt.hideKeyboard;
import static com.app.ola.core.util.ExtensionsKt.toastyError;

public class ChatActivity extends AppCompatActivity {
    private String mChannelUrl;
    private String mChannelType;
    private final static String CHANNEL_HANDLER_ID = "CHANNEL_HANDLER_CHAT";

    private ChatAdapter mChatAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ImageView mSendButton;
    private ImageView mSendButton2;
    private ImageView mSendButton3;
    private TextView titlex;
    private EditText mMessageEditText;
    String channelName = "";
    Activity act=this;
    Context context = this;
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private boolean audioRecordingPermissionGranted = false;

    private Uri filePath;
    int requestCode;
    private RecordView recordView;
    private RecordButton recordButton;
    int messageType = 1;
    String fileName;
//    ImageLoader imageLoader = Coil.imageLoader(this);
    Context ctx = this;
    //1 text
    //2 image
    //3 voicenote
    MediaRecorder recorder = new MediaRecorder();
//    private CameraKitView cameraKitView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        setContentView(R.layout.activity_chat);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        recordView = (RecordView) findViewById(R.id.record_view);
        recordButton = (RecordButton) findViewById(R.id.record_button);

//        //recorder start
//        Date currentTime = Calendar.getInstance().getTime();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        recorder.setOutputFile(currentTime.toString());
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        //recorder end

        //IMPORTANT
        recordButton.setRecordView(recordView);

        mSendButton = (ImageView) findViewById(R.id.button_chat_send);
        //mSendButton2 = (ImageView) findViewById(R.id.button_chat_send_2);
        mSendButton3 = (ImageView) findViewById(R.id.button_chat_send_3);
        titlex = (TextView) findViewById(R.id.txDoctorNameChat);

        mMessageEditText = (EditText) findViewById(R.id.edittext_chat);

        mRecyclerView = (RecyclerView) findViewById(R.id.reycler_chat);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        SharedPreferences prefs = this.getSharedPreferences(Cons.APP_NAME, Context.MODE_PRIVATE);
        mChannelUrl = prefs.getString("chat_url","00");
        channelName = prefs.getString("doctor_name","");

        String uid;
        String nname;
        uid = prefs.getString("collection_id","");
        nname = prefs.getString("username","Amig@");

        connectToSendBird(uid,nname);

        titlex.setText(channelName.toString());
//        toastyError(getApplicationContext(),mChannelUrl.toString(),true);

//        join_group_channel();

        mSendButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChatAdapter.sendMessage(mMessageEditText.getText().toString(),1);
                mMessageEditText.setText("");
                hideKeyboard(act);
            }
        });

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadPicker();
            }
        });

        //mSendButton
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (mLayoutManager.findLastVisibleItemPosition() == mChatAdapter.getItemCount() - 1) {
                    mChatAdapter.loadPreviousMessages();
                }
            }
        });


        mMessageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

//                    Toast.makeText(getApplicationContext(), "Got the focus", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(getApplicationContext(), "Lost the focus", Toast.LENGTH_LONG).show();
                }
            }
        });



        mMessageEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){

                    mSendButton.setVisibility(View.GONE);
                    recordButton.setVisibility(View.GONE);
                    mSendButton3.setVisibility(View.VISIBLE);
                }
                else{
                    mSendButton.setVisibility(View.VISIBLE);
                    recordButton.setVisibility(View.VISIBLE);
                    mSendButton3.setVisibility(View.GONE);
                }

            }
        });







        recordView.setOnRecordListener(new OnRecordListener() {
            @Override
            public void onStart() {
                //Start Recording..
                Log.d("RecordView", "onStart");
                mMessageEditText.setVisibility(View.GONE);
                mSendButton.setVisibility(View.GONE);
                recordView.setVisibility(View.VISIBLE);
                startRecording();
            }

            @Override
            public void onCancel() {
                //On Swipe To Cancel
                Log.d("RecordView", "onCancel");
                mMessageEditText.setVisibility(View.VISIBLE);
                mSendButton.setVisibility(View.VISIBLE);
                recordView.setVisibility(View.GONE);
            }

            @Override
            public void onFinish(long recordTime) {
                //Stop Recording..
                //String time = getHumanTimeText(recordTime);
                Log.d("RecordView", "onFinish");
                mMessageEditText.setVisibility(View.VISIBLE);
                mSendButton.setVisibility(View.VISIBLE);
                recordView.setVisibility(View.GONE);
                stopRecording();
                //Log.d("RecordTime", time);
            }

            @Override
            public void onLessThanSecond() {
                //When the record time is less than One Second
                Log.d("RecordView", "onLessThanSecond");
                mMessageEditText.setVisibility(View.VISIBLE);
                mSendButton.setVisibility(View.VISIBLE);
                recordView.setVisibility(View.GONE);

            }
        });

//        cameraKitView = findViewById(R.id.camera);
    }

    private void join_group_channel() {

        Log.e("USRXX",mChannelUrl);

        GroupChannel.getChannel(mChannelUrl, new GroupChannel.GroupChannelGetHandler() {
            @Override
            public void onResult(final GroupChannel groupChannel, SendBirdException e) {
                if (e != null) {
                    e.printStackTrace();
                    Log.e("Error CO: ",e.getLocalizedMessage().toUpperCase());
                    return;
                }

                mChatAdapter = new ChatAdapter(groupChannel);
                mRecyclerView.setAdapter(mChatAdapter);

                groupChannel.join(new GroupChannel.GroupChannelJoinHandler() {
                    @Override
                    public void onResult(SendBirdException e) {
                        if (e != null) {
                            e.printStackTrace();
                            Log.e("Error COD: ",e.getLocalizedMessage().toUpperCase());
                            return;
                        };

                    }
                });
            }
        });
    }

    private void join_open_channel() {
        OpenChannel.getChannel(mChannelUrl, new OpenChannel.OpenChannelGetHandler() {
            @Override
            public void onResult(final OpenChannel openChannel, SendBirdException e) {
                if (e != null) {
                    Log.e("Error CODi: ",e.getLocalizedMessage().toUpperCase());
                    e.printStackTrace();
                    return;
                }

                openChannel.enter(new OpenChannel.OpenChannelEnterHandler() {
                    @Override
                    public void onResult(SendBirdException e) {
                        if (e != null) {
                            Log.e("Error CODit: ",e.getLocalizedMessage().toUpperCase());
                            e.printStackTrace();
                            return;
                        };

                        mChatAdapter = new ChatAdapter(openChannel);
                        mRecyclerView.setAdapter(mChatAdapter);
                    }
                });
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        // Receives messages from SendBird servers
        SendBird.addChannelHandler(CHANNEL_HANDLER_ID, new SendBird.ChannelHandler() {
            @Override
            public void onMessageReceived(BaseChannel baseChannel, BaseMessage baseMessage) {
                if (baseChannel.getUrl().equals(mChannelUrl) && baseMessage instanceof UserMessage) {
                    mChatAdapter.appendMessage((UserMessage) baseMessage);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        SendBird.removeChannelHandler(CHANNEL_HANDLER_ID);

        super.onPause();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private final void connectToSendBird(String userID, final String nickname) {
        Log.e("USRXX0:","STARTING");
        SendBird.init("511DBF3B-D804-4911-813F-236B9F6E6504", (Context)this);
        SendBird.connect(userID, (SendBird.ConnectHandler)(new SendBird.ConnectHandler() {
            public final void onConnected(User user, SendBirdException e) {
                Log.e("USRXX0:","ON CONNECTED");
                Log.e("USRXX0:",user.toString());

                if (e != null) {
                    Log.e("USRXX0:",nickname);
                } else {
                    SendBird.updateCurrentUserInfo(nickname, (String)null, (SendBird.UserInfoUpdateHandler)(new SendBird.UserInfoUpdateHandler() {
                        public final void onUpdated(SendBirdException e) {
                            Log.e("USRXX","UPDATED");
                            join_group_channel();
                            if (e != null) {
                                Log.e("USRXX:",nickname);
                            }
                        }
                    }));
                }
                //

                FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(ChatActivity.this, new OnSuccessListener<InstanceIdResult>() {
                    @Override
                    public void onSuccess(InstanceIdResult instanceIdResult) {
                        SendBird.registerPushTokenForCurrentUser(instanceIdResult.getToken(), new SendBird.RegisterPushTokenWithStatusHandler() {
                            @Override
                            public void onRegistered(SendBird.PushTokenRegistrationStatus status, SendBirdException e) {
                                if (e != null) {
                                    // Handle error.
                                }
                            }
                        });
                    }
                });
                //
            }
        }));
    }

    public void loadPicker(){
//        Pix.start(ChatActivity.this,
//                requestCode);
//        Pix.start(ChatActivity.this, Options.init().setRequestCode(100));

        Options options = Options.init()
                .setRequestCode(100)                                           //Request code for activity results
                .setCount(1)                                                   //Number of images to restict selection count
                .setFrontfacing(false)                                         //Front Facing camera on start                 //Pre selected Image Urls
                .setSpanCount(4)                                               //Span count for gallery min 1 & max 5
                .setMode(Options.Mode.All)                                     //Option to select only pictures or videos or both
                .setVideoDurationLimitinSeconds(10)                            //Duration for video recording
                .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
                .setPath("/pix/images");                                       //Custom Path For media Storage

        Pix.start(ChatActivity.this, options);

        //PhotoEasy photoEasy = PhotoEasy.builder()
         //       .setActivity(this)
         //       .build();
//        ImagePicker.create(this) // Activity or Fragment
//                .start();

//        ImagePicker.create(this)
//                .folderMode(true) // folder mode (false by default)
//                .toolbarImageTitle("Tap to select") // image selection title
//                .includeVideo(true) // Show video on image picker
//                .single()
//                .showCamera(true) // show camera or not (true by default)
//                .start(); // start image picker activity with request code


        //FragmentActivity fa = new FragmentActivity();
//        Pix.start(fa,
//                requestCode);

    }

//    @Override
//    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
//
//        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
//
////            Image image = ImagePicker.getFirstImageOrNull(data);
////            Log.e("IMAGEN SELECCIONADA: ",u.toString());
////            filePath = data.getData();
////            uploadImage();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
//        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
//            Image image = ImagePicker.getFirstImageOrNull(data);
//            Log.e("1 IMAGEN SELECCIONADA",image.getPath().toString());
//            Log.e("1 IMAGEN SELECCIONADA",image.getPath().toString());
//            filePath = image.getUri();
//            uploadImage();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            String tipo = "0";//imagen
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            Log.e("Res:",returnValue.get(0).toString());

            String path = returnValue.get(0);
            if (path.contains(".mp4")){
                tipo ="1";
            }
            else{
                tipo ="0";
            }

            StringBuilder builder = new StringBuilder(path);
            builder.deleteCharAt(0);
            File file = new File(builder.toString());
            Uri imageUri = Uri.fromFile(file);

//            Log.e("data:",data.getData().toString());
//            Uri uri = Uri.parse(returnValue.get(0));
//            Log.e("URI: ",uri.toString());
            //Image image = ImagePicker.getFirstImageOrNull(data);
//            Log.e("1 IMAGEN SELECCIONADA",image.getPath().toString());
//            Log.e("1 IMAGEN SELECCIONADA",image.getPath().toString());

            Uri uri = Uri.parse(returnValue.get(0));
//            Log.e("URI:",filePath.toString());
//            try {
//                Bitmap img= MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
//                Log.e("img:",img.toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            //String filePath=PathUtil.getPath(context,yourURI);
            filePath = imageUri;
            if(tipo=="1"){
                uploadVideo();
            }
            else{
                uploadImage();
            }
            //Image image = ImagePicker.getFirstImageOrNull(returnValue);
        }
    }
    private void uploadVideo() {

        FirebaseStorage storage;
        StorageReference storageReference;

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Enviando video, espere un momento...");
            progressDialog.show();

            StorageReference ref = storageReference.child("messages/"+ UUID.randomUUID().toString()+".mp4");

            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("video/mp4")
                    .build();

            ref.putFile(filePath,metadata)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "Video enviado", Toast.LENGTH_SHORT).show();

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri downloadUrl)
                                {
                                    Log.e("DOWNLOADURI",downloadUrl.toString());
                                    mChatAdapter.sendMessage(downloadUrl.toString(),2);
                                    //do something with downloadurl
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "Falló el envío del video"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Enviando "+(int)progress+"%");
                        }
                    })
//                    .
//                    addOnCompleteListener((OnCompleteListener<UploadTask.TaskSnapshot>) downloadUrl -> {
//                        Log.e("DOWNLOADURI",downloadUrl.toString());
//                        //do something with downloadurl
//                    })
            ;



        }
    }
    private void uploadImage() {

        FirebaseStorage storage;
        StorageReference storageReference;

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Enviando imagen...");
            progressDialog.show();

            StorageReference ref = storageReference.child("messages/"+ UUID.randomUUID().toString()+".jpg");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "Imagen enviada", Toast.LENGTH_SHORT).show();

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri downloadUrl)
                                {
                                    Log.e("DOWNLOADURI",downloadUrl.toString());
                                    mChatAdapter.sendMessage(downloadUrl.toString(),2);
                                    //do something with downloadurl
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "Falló el envío de la imagen "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Enviando "+(int)progress+"%");
                        }
                    })
//                    .
//                    addOnCompleteListener((OnCompleteListener<UploadTask.TaskSnapshot>) downloadUrl -> {
//                        Log.e("DOWNLOADURI",downloadUrl.toString());
//                        //do something with downloadurl
//                    })
            ;



        }
    }


    private void uploadVoiceNote(Uri px) {

        FirebaseStorage storage;
        StorageReference storageReference;

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        filePath=px;
        Log.e("FiletoUpload:",filePath.toString());
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Enviando nota de voz...");
            progressDialog.show();

            StorageReference ref = storageReference.child("messages/voice_notes/OLAVN_"+ UUID.randomUUID().toString()+".m4a");

            StorageMetadata metadata = new StorageMetadata.Builder()
                    .setContentType("audio/x-m4a")
                    .build();

            ref.putFile(filePath,metadata)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "Nota de voz enviada", Toast.LENGTH_SHORT).show();

                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri downloadUrl)
                                {
                                    Log.e("DOWNLOADURI",downloadUrl.toString());
                                    mChatAdapter.sendMessage(downloadUrl.toString(),2);
                                    //do something with downloadurl
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ChatActivity.this, "Falló el envío de la nota de voz "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Enviando "+(int)progress+"%");
                        }
                    })
//                    .
//                    addOnCompleteListener((OnCompleteListener<UploadTask.TaskSnapshot>) downloadUrl -> {
//                        Log.e("DOWNLOADURI",downloadUrl.toString());
//                        //do something with downloadurl
//                    })
            ;



        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                audioRecordingPermissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }

        if (!audioRecordingPermissionGranted) {
            finish();
        }
    }

    private void startRecording() {
        String uuid = UUID.randomUUID().toString();
//        Date filename = Calendar.getInstance().getTime();
//        fileName = filename.toString();
//        fileName = getExternalCacheDir().getAbsolutePath() + "/" + uuid + ".3gp";
        fileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        fileName += "/"+uuid+".m4a";
//        Log.e("Filename",fileName);
//        Log.i(MainActivity.class.getSimpleName(), fileName+"M4A");


        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.HE_AAC);
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(ChatActivity.class.getSimpleName() + ":startRecording()", "prepare() failed");
        }

        recorder.start();
    }

    private void stopRecording() {
        if (recorder != null) {
            recorder.release();
            recorder = null;

            uploadVoiceNote(Uri.fromFile(new File(fileName)));
        }
    }




    private class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int VIEW_TYPE_MESSAGE_SENT = 1; //text
        private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2; //text

        private static final int VIEW_TYPE_IMAGE_SENT = 3; //image
        private static final int VIEW_TYPE_IMAGE_RECEIVED = 4; //image

        private static final int VIEW_TYPE_VN_SENT = 5; //voicenote
        private static final int VIEW_TYPE_VN_RECEIVED = 6; //voicenote


        private ArrayList<BaseMessage> mMessageList;
        private BaseChannel mChannel;

        ChatAdapter(BaseChannel channel) {
            mMessageList = new ArrayList<>();
            mChannel = channel;

            refresh();
        }

        // Retrieves 30 most recent messages.
        void refresh() {
            mChannel.getPreviousMessagesByTimestamp(Long.MAX_VALUE, true, 30, true,
                    BaseChannel.MessageTypeFilter.USER, null, new BaseChannel.GetMessagesHandler() {
                        @Override
                        public void onResult(List<BaseMessage> list, SendBirdException e) {
                            if (e != null) {
                                Log.e("Error CODiT: ",e.getLocalizedMessage().toUpperCase());
                                e.printStackTrace();
                                return;
                            }
                            mMessageList = (ArrayList<BaseMessage>) list;
                            notifyDataSetChanged();
                        }
                    });

        }

        void loadPreviousMessages() {
            final long lastTimestamp = mMessageList.get(mMessageList.size() - 1).getCreatedAt();
            mChannel.getPreviousMessagesByTimestamp(lastTimestamp, false, 30, true,
                    BaseChannel.MessageTypeFilter.USER, null, new BaseChannel.GetMessagesHandler() {
                        @Override
                        public void onResult(List<BaseMessage> list, SendBirdException e) {
                            if (e != null) {
                                Log.e("Error CODiTy: ",e.getLocalizedMessage().toUpperCase());
                                e.printStackTrace();
                                return;
                            }
                            mMessageList.addAll(list);

                            notifyDataSetChanged();
                        }
                    });
        }

        // Appends a new message to the beginning of the message list.
        void appendMessage(UserMessage message) {
            mMessageList.add(0, message);
            Log.e("MESSAGE:",message.toString());
            notifyDataSetChanged();
        }

        // Sends a new message, and appends the sent message to the beginning of the message list.
        void sendMessage(final String message,int msgType) {
            mChannel.sendUserMessage(message, new BaseChannel.SendUserMessageHandler() {
                @Override
                public void onSent(UserMessage userMessage, SendBirdException e) {
                    if (e != null) {
                        Log.e("Error CODiTy 1: ",e.getLocalizedMessage().toUpperCase());
                        e.printStackTrace();
                        return;
                    }

                    mMessageList.add(0, userMessage);
                    notifyDataSetChanged();
                }
            });
        }

        // Determines the appropriate ViewType according to the sender of the message.
        @Override
        public int getItemViewType(int position) {
            UserMessage message = (UserMessage) mMessageList.get(position);

            if(message.toString().contains(".m4a?alt")){
                if(message.getSender().getUserId().equals(SendBird.getCurrentUser().getUserId())){
                    return VIEW_TYPE_VN_SENT;
                }
                else{
                    return VIEW_TYPE_VN_RECEIVED;
                }
            }

            if(message.toString().contains(".jpg?alt")||message.toString().contains(".mp4?alt")){
                if(message.getSender().getUserId().equals(SendBird.getCurrentUser().getUserId())){
                    return VIEW_TYPE_IMAGE_SENT;
                }
                else{
                    return VIEW_TYPE_IMAGE_RECEIVED;
                }
            }

            if (message.getSender().getUserId().equals(SendBird.getCurrentUser().getUserId())) {
                // If the current user is the sender of the message
                return VIEW_TYPE_MESSAGE_SENT;
            } else {
                // If some other user sent the message
                return VIEW_TYPE_MESSAGE_RECEIVED;
            }
        }

        // Inflates the appropriate layout according to the ViewType.
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;

            if (viewType == VIEW_TYPE_MESSAGE_SENT) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_sent, parent, false);
                return new SentMessageHolder(view);
            } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_message_received, parent, false);
                return new ReceivedMessageHolder(view);
            }


            if (viewType == VIEW_TYPE_IMAGE_SENT) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_sent, parent, false);
                return new SentMessageImageHolder(view);
            } else if (viewType == VIEW_TYPE_IMAGE_RECEIVED) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_received, parent, false);
                return new ReceivedMessageImageHolder(view);
            }

            if (viewType == VIEW_TYPE_VN_SENT) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vn_sent, parent, false);
                return new SentMessageVNHolder(view);
            } else if (viewType == VIEW_TYPE_VN_RECEIVED) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vn_received, parent, false);
                return new ReceivedMessageVNHolder(view);
            }

            return null;
        }

        // Passes the message object to a ViewHolder so that the contents can be bound to UI.
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            UserMessage message = (UserMessage) mMessageList.get(position);


            switch (holder.getItemViewType()) {
                case VIEW_TYPE_MESSAGE_SENT:
                    ((SentMessageHolder) holder).bind(message);
                    break;
                case VIEW_TYPE_MESSAGE_RECEIVED:
                    ((ReceivedMessageHolder) holder).bind(message);
                    break;

                case VIEW_TYPE_IMAGE_RECEIVED:
                    ((ReceivedMessageImageHolder) holder).bind(message);
                    break;
                case VIEW_TYPE_IMAGE_SENT:
                    ((SentMessageImageHolder) holder).bind(message);
                    break;
//
                case VIEW_TYPE_VN_RECEIVED:
                    try {
                        ((ReceivedMessageVNHolder) holder).bind(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case VIEW_TYPE_VN_SENT:
                    try {
                        ((SentMessageVNHolder) holder).bind(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }

        @Override
        public int getItemCount() {
            return mMessageList.size();
        }


        private  class SentMessageHolder extends RecyclerView.ViewHolder {
            TextView messageText, timeText;
            SentMessageHolder(View itemView) {
                super(itemView);
                messageText = (TextView) itemView.findViewById(R.id.text_message_body);
                timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            }
            void bind(UserMessage message) {
                messageText.setText(message.getMessage());
            }
        }

        private  class ReceivedMessageHolder extends RecyclerView.ViewHolder {
            TextView messageText, timeText, nameText;
            ImageView profileImage;
            ReceivedMessageHolder(View itemView) {
                super(itemView);
                messageText = (TextView) itemView.findViewById(R.id.text_message_body_rec);
                timeText = (TextView) itemView.findViewById(R.id.text_message_time_rec);
//                nameText = (TextView) itemView.findViewById(R.id.text_message_name);
                //profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
            }

            void bind(UserMessage message) {
                try {
                    messageText.setText(message.getMessage());
                    nameText.setText(message.getSender().getNickname());
                }
                catch (Exception s){

                }
            }
        }




        private class SentMessageImageHolder extends RecyclerView.ViewHolder {
            TextView messageText, timeText;
            ImageView messageImage;
            SentMessageImageHolder(View itemView) {
                super(itemView);
                messageImage = (ImageView) itemView.findViewById(R.id.image_message_body);
                timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            }
            void bind(UserMessage message) {
                Log.e("BIND   --->",message.toString());
                Glide.with(ctx).load(message.getMessage().toString()).into(messageImage);
                messageImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        zoom
                        SharedPreferences prefs = ctx.getSharedPreferences(Cons.APP_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor prefEditor = prefs.edit();
                        if(message.getMessage().contains(".jpg")){
                            Log.e("ES foto","ES foto");
                            prefEditor.putString("zoomImage", message.getMessage().toString());
                            prefEditor.putString("zoomVideo", "");
                        }
                        else if(message.getMessage().contains(".mp4")){
                            Log.e("ES ESVIDEO","ES VIDEO");
                            prefEditor.putString("zoomVideo", message.getMessage().toString());
                            prefEditor.putString("zoomImage", "");
                        }

                        prefEditor.commit();
                        prefEditor.apply();
                        Intent intent = new Intent(ctx, ZoomImageActivity.class);
                        ctx.startActivity(intent);
                    }
                });
            }
        }

        private  class ReceivedMessageImageHolder extends RecyclerView.ViewHolder {
            TextView messageText, timeText;
            ImageView messageImage;
            ReceivedMessageImageHolder(View itemView) {
                super(itemView);
                messageImage = (ImageView) itemView.findViewById(R.id.image_message_body);
                timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            }
//            void bind(UserMessage message) {
//                messageText.setText(message.getMessage());
//            }
void bind(UserMessage message) {
    Log.e("BIND   --->",message.toString());
    Glide.with(ctx).load(message.getMessage().toString()).into(messageImage);
    messageImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//                        zoom
            SharedPreferences prefs = ctx.getSharedPreferences(Cons.APP_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = prefs.edit();
//            prefEditor.putString("zoomVideo", message.getMessage().toString());
            if(message.getMessage().contains(".jpg")){
                Log.e("ES FOTO","ESFOTO");
                prefEditor.putString("zoomImage", message.getMessage().toString());
                prefEditor.putString("zoomVideo", "");
            }
            else if(message.getMessage().contains(".mp4")){
                Log.e("ES ESVIDEO","ES VIDEO");
                prefEditor.putString("zoomVideo", message.getMessage().toString());
                prefEditor.putString("zoomImage", "");
            }
            prefEditor.commit();
            prefEditor.apply();
            Intent intent = new Intent(ctx, ZoomImageActivity.class);
            ctx.startActivity(intent);
        }
    });
}
        }



        private  class SentMessageVNHolder extends RecyclerView.ViewHolder {
            TextView messageText, timeText;
            LinearLayout lnvn;
            ImageView btnPlayStop;
            SentMessageVNHolder(View itemView) {
                super(itemView);
                //messageText = (TextView) itemView.findViewById(R.id.text_message_body);
                lnvn = (LinearLayout) itemView.findViewById(R.id.ic_VN);
                btnPlayStop = (ImageView) itemView.findViewById(R.id.btnPlayStop);
                timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            }
            void bind(UserMessage message) throws IOException {
//                messageText.setText(message.getMessage());

                lnvn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Log.e("PLAYING",message.getMessage().toString());

                            MediaPlayer player = new MediaPlayer();
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            player.setDataSource(message.getMessage().toString());
                            player.prepare();
                            player.start();
                            btnPlayStop.setImageResource(R.drawable.ic_stop);
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    try {
                                        Log.e("FINISHED","FINISHED");
                                        btnPlayStop.setImageResource(R.drawable.ic_play_button_arrowhead);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    }
                });


            }
        }
        private  class ReceivedMessageVNHolder extends RecyclerView.ViewHolder {

            TextView messageText, timeText;
            LinearLayout lnvn;
            ImageView btnPlayStop;
            ReceivedMessageVNHolder(View itemView) {
                super(itemView);
                //messageText = (TextView) itemView.findViewById(R.id.text_message_body);
                lnvn = (LinearLayout) itemView.findViewById(R.id.ic_VN_c);
                btnPlayStop = (ImageView) itemView.findViewById(R.id.btnPlayStop_c);
                timeText = (TextView) itemView.findViewById(R.id.text_message_time_c);
            }
            void bind(UserMessage message) throws IOException {
//                messageText.setText(message.getMessage());

                lnvn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Log.e("PLAYING",message.getMessage().toString());

                            MediaPlayer player = new MediaPlayer();
                            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            player.setDataSource(message.getMessage().toString());
                            player.prepare();
                            player.start();
                            btnPlayStop.setImageResource(R.drawable.ic_stop);
                            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    try {
                                        Log.e("FINISHED","FINISHED");
                                        btnPlayStop.setImageResource(R.drawable.ic_play_button_arrowhead);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    }
                });
            }
        }

    }



}