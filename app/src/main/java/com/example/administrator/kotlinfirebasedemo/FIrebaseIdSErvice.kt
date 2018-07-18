package com.example.administrator.kotlinfirebasedemo

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class FIrebaseIdSErvice : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.e("token", refreshedToken)
        //fn2mh5gfIB8:APA91bFT6qWhuy5CTKnHZzgE1b9_UsCHSxwKH2mmoQ0SUSTgUE11bmJHvYY0Q57oPE7FHJWfFm8AaR4qe4puywyOk3ZInWAjFHalKs3lzdj7lN4lCZuCDV_R5lC4sVPaq9_PLpOtZ8HKCib2XKx6m2Xp-qoZhAMzfw
    }
}