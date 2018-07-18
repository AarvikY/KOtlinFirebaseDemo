package com.example.administrator.kotlinfirebasedemo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.TextView
import android.content.Intent
import android.view.View
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.HashMap
import android.view.ViewGroup
import android.widget.Button
import com.crashlytics.android.Crashlytics




class MainActivity : AppCompatActivity() {
    private var mRemoteConfig: FirebaseRemoteConfig? = null
    private var txt_remote_config: TextView? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mRemoteConfig = FirebaseRemoteConfig.getInstance()
      //  Crashlytics.getInstance().crash() // Force a crash
      //  txt_remote_config!!.setText(mRemoteConfig!!.getString("remote_test_parameter"))
        val firebaseRemoteConfigSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build()
        // Define default configuration values. It can be used in case where
        // config not fetched due to any issue
        val defaultConfigMap = HashMap<String, Any>()
        defaultConfigMap["remote_test_parameter"] = 10

        // Apply the configuration settings and default values of remote config.
        mRemoteConfig!!.setConfigSettings(firebaseRemoteConfigSettings)
        mRemoteConfig!!.setDefaults(defaultConfigMap)
        txt_remote_config = findViewById<TextView>(R.id.remoteconfig)
        //calling the loadConfig Method to fetch the remote configuration
        loadConfig()


        val crashButton = Button(this)
        crashButton.setText("Crash!")
        crashButton.setOnClickListener {

        }
        addContentView(crashButton,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT))

        val intent = intent
        if (intent != null && intent.extras != null) {
            val message = intent.getStringExtra("message")
            if(!message.isNullOrEmpty()) {
                AlertDialog.Builder(this)
                        .setTitle("Notification")
                        .setMessage(message)
                        .setPositiveButton("Ok", { dialog, which -> }).show()
            }
        }

    }


    private fun loadConfig() {

        var cacheExpiration: Long = 3600 // we set here 1 hours in seconds

        // If developer mode is enabled we need to reduce cacheExpiration to 0 so that
        // every time our app fetch the config from remote server.
        // remove the below line of if condition code in release version
        if (mRemoteConfig!!.getInfo().configSettings.isDeveloperModeEnabled) {
            cacheExpiration = 0
        }
//i am changing the data new datatattatjdjjf fgfdf
        //this is check to test the githun

        mRemoteConfig!!.fetch(cacheExpiration)
                .addOnSuccessListener {
                    mRemoteConfig!!.activateFetched()
                    //calling the ApplyConfig method to apply the fetch configuration
                    applyConfig()
                }
                .addOnFailureListener { e ->
                    Log.w("RemoteConfig", "Error fetching config: " + e.message)
                    // On error we can apply different method or we can use the same as well
                    // As we have already set the default value
                    applyOnFailure()
                }
    }

    @SuppressLint("SetTextI18n")
    private fun applyOnFailure() {
        val remote_value = mRemoteConfig!!.getString("remote_test_parameter")
        txt_remote_config!!.text = "Remote Config fetch failed, Setting Default value is:$remote_value"

    }

    @SuppressLint("SetTextI18n")
    private fun applyConfig() {
        val remote_value = mRemoteConfig!!.getString("remote_test_parameter")

        txt_remote_config!!.text = "Remote Config fetch, value is:$remote_value"

    }


}
