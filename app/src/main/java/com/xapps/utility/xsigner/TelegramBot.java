package com.xapps.utility.xsigner;

import android.sun.security.BuildConfig;
import android.util.Log;
import okhttp3.*;
import java.io.IOException;

public class TelegramBot {
    private static final String BOT_TOKEN = com.xapps.utility.xsigner.BuildConfig.BOT_TOKEN;
    private static final String CHAT_ID = com.xapps.utility.xsigner.BuildConfig.CHAT_ID;
    private static final OkHttpClient client = new OkHttpClient();

    public static void sendMessage(String message) {
        HttpUrl url = HttpUrl.parse("https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage")
                .newBuilder()
                .addQueryParameter("chat_id", CHAT_ID)
                .addQueryParameter("message_thread_id", "5")
                .addQueryParameter("text", message)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TelegramBot", "Failed to send message", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    Log.e("TelegramBot", "Error response: " + response.toString());
                } else {
                    Log.d("TelegramBot", "Message sent successfully!");
                }
                response.close();
            }
        });
    }
}