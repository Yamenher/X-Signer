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

    public static void sendMessage(String messageText) {
        // If botToken or chatId is null, retry after 300ms
        if (botToken == null || chatId == null) {
            Log.e("Error", "Bot token or chat ID not available, retrying...");
            handler.postDelayed(() -> sendMessage(messageText), 300);
            return;
        }

        try {
            String message = URLEncoder.encode(messageText, "UTF-8");
            botToken = /*(String) map.get("bot_token")*/"8128724883:AAGC7BkP6kR7LfRucKt6kPu_QmqKFJ5iYQU";
            chatId = /*(String) map.get("chat_id")*/"-1002479736701";
            topicId = /*(String) map.get("topic_id")*/"8";
            String urlString = "https://api.telegram.org/bot" + botToken + "/sendMessage?chat_id=" + chatId + "&text=" + message;
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                Log.wtf("info",response.toString());
            } else {
                Log.wtf("Error", "Failed to send message. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }
    }

    public static String getErrorType() {
        return error;
    }
}