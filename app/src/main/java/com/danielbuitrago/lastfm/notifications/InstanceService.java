package com.danielbuitrago.lastfm.notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class InstanceService extends FirebaseMessagingService {
    public static final String TAG = "NOTICIAS";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG, "Token" + token);
    }
}
