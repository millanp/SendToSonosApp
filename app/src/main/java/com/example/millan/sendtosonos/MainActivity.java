package com.example.millan.sendtosonos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action)) {
            if ("text/plain".equals(type)) {
                sendToServer(intent);
            }
        }

        setContentView(R.layout.activity_main);
    }

    private void sendToServer(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Using minified YouTube URL...
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://sendtosonos.synology.me:8000/play/";
            url = url.concat(sharedText);
            StringRequest req = new StringRequest(Request.Method.GET, url, null, null);
            queue.add(req);
            finish();
        }
    }
}
