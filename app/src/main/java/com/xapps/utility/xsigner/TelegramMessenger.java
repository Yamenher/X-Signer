package com.xapps.utility.xsigner;

import android.os.Handler;
import android.util.Log;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

public class TelegramMessenger {
    private static String botToken;
    private static String chatId;
    private static String topicId;
    private static String error = "NOT";
    private static Handler handler = new Handler();

    public TelegramMessenger() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("telegramData");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        if (map != null) {
                            botToken = /*(String) map.get("bot_token")*/"8128724883:AAGC7BkP6kR7LfRucKt6kPu_QmqKFJ5iYQU";
                            chatId = /*(String) map.get("chat_id")*/"-1002479736701";
                            topicId = /*(String) map.get("topic_id")*/"8";
                            error = "NOT";
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseError.getCode() == DatabaseError.NETWORK_ERROR) {
                    error = "OFFLINE";
                } else {
                    error = "UNKNOWN";
                }
            }
        });
    }
}